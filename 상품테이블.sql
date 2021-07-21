--상품
	--상품ID : 몰ID+code
	--상품명
	--제조원/수입원
	--판매가격
	--일반소비자가격
	--카드할부여부
	--검색어
	--상품설명
	--상품등록일자
	--상품사진경로
	
DROP TABLE Product ;
CREATE TABLE Product (
       ProductID              VARCHAR(7) NOT NULL,
       MallID		     VARCHAR(4) NOT NULL,
       ProductName           VARCHAR(40) NOT NULL,
       Company              VARCHAR(40) NOT NULL,
       Price1               int NOT NULL,
       Price2               int ,
       Install              CHAR(1) NOT NULL,
       keyword              VARCHAR(50) NOT NULL,
       Detail               VARCHAR(100) ,
       ProductDate         DATE NOT NULL,
       PHOTODIR	VARCHAR(100)	 
);

ALTER TABLE Product
       ADD   PRIMARY KEY (ProductID, MallID)  ;

--장바구니
	--주문번호
	--상품ID
	--회원ID
	--수량
	--가격
DROP TABLE Basket ;
CREATE TABLE Basket (
       OrderNum             int NOT NULL,
       ProductID            VARCHAR(7) NOT NULL,
       MemID		    VARCHAR(15) NOT NULL,	
       Quantity             int NOT NULL,
       Price                int NOT NULL
);

ALTER TABLE Basket
       ADD   PRIMARY KEY (OrderNum)  ;

--구매자
	--주문번호
	--주소구분(1:집,2:직장)
	--회원ID
	--수취인이름
	--수취인주소
	--연락처
	--이메일
	--결재방법(CARD / REMIT)
	--구매상품종류
	--총구매액
	--처리상태(Y/N)
	--카드종류
	--카드번호
DROP TABLE Purchaser ;
CREATE TABLE Purchaser (
       OrderNum             int NOT NULL,
       Place		    CHAR(1) NOT NULL,
       MemID                VARCHAR(15) NOT NULL,
       Name                 VARCHAR(20) NOT NULL,
       Address              VARCHAR(70) NOT NULL,
       Tel		    VARCHAR(20) ,
       Email		    VARCHAR(60) ,
       PayType              CHAR(5) NOT NULL,
       Tcount               int ,
       Amount               int NOT NULL,
       PayStatus            CHAR(1) ,
       PurchaseDate         DATE NOT NULL,
       CardType		    VARCHAR(10) ,		
       CardNumber	    VARCHAR(16) 
);

ALTER TABLE Purchaser
       ADD   PRIMARY KEY (OrderNum)  ;


commit;