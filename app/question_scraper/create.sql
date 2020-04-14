CREATE TABLE "question"
  ( 
     `question_id`   INTEGER NOT NULL,
     `subject`       TEXT NOT NULL,
     `question`      TEXT NOT NULL,
     `answer_a`      TEXT NOT NULL, 
     `answer_b`      TEXT NOT NULL, 
     `answer_c`      TEXT NOT NULL, 
     `answer_d`      TEXT NOT NULL, 
     `answer_e`      TEXT NOT NULL, 
     `solution`      TEXT NOT NULL,
     PRIMARY KEY(`question_id`, `subject`)
  )