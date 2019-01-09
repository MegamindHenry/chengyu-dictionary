### Project Proposal

We want to create a Chinese-English Chengyu (Four-Character Idiom) Dictionary that is useful both to native speakers and (middle-level to advanced) language learners, or anyone interested in Chengyu in general.

#### Motivation/Gap

We do have Chinese Chengyu Dictioanry, although most of them are in printed version. However, there is no well-formulated Chinese-English Chengyu Dictionary either in print or online. We have found some online database covering only Chengyu in Chinese, but no online dictionary in English or database with English translation for both literal and figurative meaning.

#### Surface-level Description
This Chinese-English Chengyu Dictionary allows you to search either by Chinese or English or Pinyin. All of them are with fuzzy-matching enabled.
Also, a user can search with provided tags, such as "numbers", "animals" and even forms like "一五一十" with the first and third characters being the same can be represented with an "AXAY" tag. 

#### Examples of use cases
For example, you can search by "one stone two birds" ,then "一石二鸟" would come up, with its original Chinese lexeme "一石二鸟", its Pinyin “Yi Shi Er Niao”, its literal meaning "one stone two birds", its figurative meaning "doing one thing gets multiple benifits", and its tags, "number" and "animal". You can also search by its literal or figurative meaning. In addition to that, you can select tags, such as "number", then every 4-character idiom that contain at least one number character (e.g. "七上八下": 'seven up eight down') would come up. You can also search with form tags which I mentioned above. Let's say "AXAY", then all idioms with first and third character being the same come up.

#### Resources
Right now, there are several database covered in Chinese. 

* Include entries from Xinhua Dictionary (but actually obtained via another dictionary website: http://www.zd9999.com): https://github.com/pwxcoo/chinese-xinhua 
* a collection of Chinese idioms collected from the web: https://github.com/by-syk/chinese-idiom-db

References to English translations of figurative meaning:
https://www.chinese-tools.com/chinese/chengyu/dictionary
https://www.saporedicina.com/english/list-chengyu/

However, we need to translate them into English in both literal and figurative meanings. We plan to have 100-150 entries to worked on (with translations) but we can include much more entries in Chinese.

#### Feature List

Enhancing information:
 Include tags/badges for each Chenyu which gives:
 1.  structural information (common forms are: AABB, ABAC, AABC, BCAA...), great to have for users who want to solve Chengyu puzzles
 2.  character information (tells if the Chengyu contain at least one character which represent animal, number, organs, people...)
 3.  semantic information (positive/negative/neutral; is story-based or not)

Entry Representation:

* English mode:

Lemma (a 4-character idiom) [pinyin]
Literal meaning (in English)
Figurative usage
(Origin/Example sentence[bilingual]/select for export)

* Chinese mode:

Lemma (a 4-character idiom) [pinyin]
Meaning and usage information in Chinese
(Origin/Example sentence/select for export)

* Bilingual mode:

Lemma (a 4-character idiom) [pinyin]
Literal meaning (in English, useless in Chinese)
Figurative usage in both Chinese and English
(Origin/Example sentence/select for export)


#### Technology
We want to use GWT as our major framework for the web application (like in the practice assignments). We would use mySQL to create a database to store our data. And we would use Bootstrap as our UI framework.


#### Nice-to-have features

1. origin:

  It is better to have a origin for Chengyu because a large part of them are coming from stories. If we had time, we would like to present the origins in both Chinese and English. Most translations must be done manually. The origin of a Chengyu is crucial to the understanding of its meaning.

2. example sentence:

  Another nice-to-have feature is example sentences, they are easy to find in Chinese and they provide the context of usage (which is probably already covered in explaining the figurative meaning of a Chengyu), but how to translate them into English poses a problem.

3. select and export:

  Export the marked Chengyu to a .csv file, makes it easier for learners to select the entries they want and add to flashcard sets, e.g. to Quizlet
