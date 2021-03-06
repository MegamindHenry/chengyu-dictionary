CREATE TABLE Chengyu (
	ID INT NOT NULL,
	Abbr VARCHAR(10),
	Chinese VARCHAR(10),
	EnglishLiteral VARCHAR(100),
	EnglishFigurative VARCHAR(100),
	Pinyin VARCHAR(40),
	Example VARCHAR(255),
	ExampleTranslation VARCHAR(1000),
	Origin TEXT,
	OriginTranslation TEXT,
	Frequency SMALLINT,
	CONSTRAINT PK_Chengyu PRIMARY KEY (ID)
);

CREATE TABLE Tags (
	ID INT NOT NULL,
	Tag VARCHAR(10),
	CONSTRAINT PK_Tag PRIMARY KEY (ID)
);

CREATE TABLE ChengyuTag (
	ChengyuID INT NOT NULL,
	TagID INT NOT NULL,
	CONSTRAINT PK_Chengyu_Tag PRIMARY KEY (ChengyuID, TagID),
	CONSTRAINT FK_Chengyu FOREIGN KEY (ChengyuID) REFERENCES Chengyu(ID),
	CONSTRAINT FK_Tag FOREIGN KEY (TagID) REFERENCES Tags(ID)
);
