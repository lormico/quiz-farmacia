#!/usr/bin/env python3
# coding: utf-8
import os
import re
import sqlite3
import sys

from typing import List

DIR = os.path.dirname(os.path.realpath(__file__))
SQL_CREATE_FILE = os.path.join(DIR, 'create.sql')
INPUT_FILE = os.path.join(DIR,'raw.txt')
OUTPUT_FILE = os.path.join(DIR, 'question.sqlite')

class Analyzer:
    def __init__(self, cursor: sqlite3.Cursor):
        self.cursor = cursor

        self.question_id = 1
        self.subject = ''
        self.question = ''
        self.answers: List[str] = []
        self.solution = -1

        self.answer_index = -1

    def finalize_question(self):
        self.question = re.sub(r"(A|B|C|D|E)[\W]*$", r"", self.question).rstrip()
        self.cursor.execute(
            """INSERT INTO question VALUES (
                :question_id, :subject, :question, :a, :b, :c, :d, :e, :solution
            )""", (
                self.question_id,
                self.subject,
                self.question,
                *self.answers,
                chr(self.solution + 65)
            )
        )
        self.answers.clear()
        self.question = ''
        self.solution = -1
        self.question_id += 1
        self.answer_index = -1

    def add_line(self, line: str):
        if line.startswith("Elenco"):
            self.subject = re.sub(r".*\W ", r"", line.strip())
            self.question_id = 1
            return

        m = re.match(r"(X?)\(?(\w)\)[ ]?(.*)", line)
        if m:
            self.answer_index = ord(m.group(2)) - 65
            if m.group(1):
                self.solution = self.answer_index

            answer = m.group(3)
            if self.answer_index == 4:
                n = re.match(r"(.*[a-z])([A-Z].*)", answer)
                if n:
                    self.answers.append(n.group(1))
                    self.finalize_question()
                    self.question += n.group(2) + ' '
                else:
                    self.answers.append(answer)
                    self.finalize_question()
            else:
                self.answers.append(m.group(3))
        elif self.answer_index >= 0:
            self.answers[-1] += ' ' + line.replace('\n', '')
        else:
            self.question += line.replace('\n', ' ')

if __name__ == '__main__':
    try:
        os.remove(OUTPUT_FILE)
    except FileNotFoundError:
        pass

    create_query: str
    with open(SQL_CREATE_FILE) as sql_file:
        create_query = ''.join(sql_file.readlines())

    with sqlite3.connect(OUTPUT_FILE) as conn, open(INPUT_FILE) as raw:
        cur = conn.cursor()
        cur.execute(create_query)

        analyzer = Analyzer(cur)
        for i, line in enumerate(raw):
            analyzer.add_line(line)
            if i % 100 == 0:
                conn.commit()
