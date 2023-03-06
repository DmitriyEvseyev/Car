# car-manager

Overall todo list:
- DAO: https://github.com/DmitriyEvseyev/Car/tree/nigo_comments/controller/database should be moved to dao package
  - this logic should be moved to car dao -> https://github.com/DmitriyEvseyev/Car/blob/nigo_comments/controller/database/MaxId.java#L14 and create SQL Query which will return as result max id - read https://stackoverflow.com/questions/16936135/postgresql-select-from-max-id
  - create DAO Manager class - the main responsibilities of calss:
     - create connection - move this logic https://github.com/DmitriyEvseyev/Car/blob/nigo_comments/controller/database/Connection.java#L5
     - provide get method from which you can get CarDAO -> in controller you will have DAOManager.getCarDao()
     - move https://github.com/DmitriyEvseyev/Car/blob/nigo_comments/controller/database/CreateTable.java to CarDAO
- rename project repository to car-manager (lower case)
- create .gitignore file in root -> https://github.com/github/gitignore/blob/main/Global/JetBrains.gitignore
    
