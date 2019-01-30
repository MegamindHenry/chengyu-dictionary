import json

idioms = list()

with open("chinese-idioms.txt", "r") as f:
	for line in f:
		parts = line.strip().split(",")
		# idiom = parts[1]
		idioms.append(parts)

print("All chinese idioms: ", len(idioms))

print("loading file...")

file  = open('zh_cn.txt', 'r').read()

data_list = list()

total_idioms = len(idioms)

for k, idiom in enumerate(idioms[:10]):
	count = file.count(idiom[1])
	frequency = {
		"ID": idiom[0].strip('"'),
		"Abbr": idiom[6].strip('"'),
		"Chinese": idiom[1].strip('"'),
		"ChineseExplanation": idiom[3].strip('"'),
		"EnglishLiteral": "N/A",
		"EnglishFigurative": "N/A",
		"Pinyin": idiom[2].strip('"'),
		"Example": idiom[5].strip('"'),
		"ExampleTranslation": "N/A",
		"Origin": idiom[4].strip('"'),
		"OriginTranslation": "N/A",
		"Frequency": count
		}
	print("Idiom: ", idiom[1], k, "/", total_idioms, end="\r")
	data_list.append(frequency)

print("")

sorted_list = sorted(data_list, key=lambda k: k['Frequency'], reverse=True)

output_string = json.dumps(sorted_list, indent=4, ensure_ascii=False)

with open("data.txt", "w+") as f:
	f.write(output_string)