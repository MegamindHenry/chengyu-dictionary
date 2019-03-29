#How to install
To install our application, please follow the listed instructions:

1.  First, you need an environment where GWT can be deployed. At this moment, we are using Eclipse plus a GWT plugin where we retrieved it from Eclipse marketplace.
2. Then, MySQL is needed in order to import our data.
  * MySQL Workbench is optional but it helped comparing to MySQL command line tools.
3. To import our data, you need to run `data\_structure.sql` file where our data structures and relations were stored.
4. Then, you should first import Chengyu data from `chengyu\_data.json` file and tags data from `tags.csv` file, since there are foreign key dependencies.
5. Then, you can import chengyu tags relations from `chengyu\_tag.csv` file.
6. Following that, you need to go to `DictionaryServiceImpl.java` (located in `chengyu.dict\src\main\java\com\colewews1819\server\`) where database connection information was hard-coded in. Variables `DB\_URL`, `USER` and `PASS` need to be modified in order to make our program connect to the database.
7. Please run the application through Eclipse.
8. Open the page `http://localhost:8888/chengyudict` via a browser. We have tested and passed with Safari, Google Chrome and Firefox.
9. Enjoy it!