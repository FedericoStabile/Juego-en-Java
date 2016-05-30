
create table FILTER
(
	id_filter       numeric(10,0) identity (1,1),
	filter_nombre   nvarchar(255),


	PRIMARY KEY (id_filter)
)



create table ALBUM
(
	id_album          numeric(10,0) identity (1,1),
	album_nombre      nvarchar(255),
	album_dicImagen   nvarchar(255),
	fecha_modificacion  datetime,


	PRIMARY KEY (id_album)
)


create table FILTER_ALBUM
( 
	fa_album      numeric(10,0)      NOT NULL,
	fa_filter     numeric(10,0)      NOT NULL,

    PRIMARY KEY	(fa_album, fa_filter), 
	FOREIGN KEY (fa_album)      references ALBUM(id_album), 
	FOREIGN KEY (fa_filter)     references FILTER(id_filter) 
	
)


create table LABEL
(
	id_label       numeric(10,0) identity (1,1),
	label_nombre   nvarchar(255),
	filter         numeric(10,0),

	FOREIGN KEY (filter)           references FILTER(id_filter), 
	PRIMARY KEY (id_label)
)


create table LABEL_ALBUM
( 
	la_album      numeric(10,0)      NOT NULL,
	la_label     numeric(10,0)       NOT NULL,

    PRIMARY KEY	(la_album, la_label), 
	FOREIGN KEY (la_album)      references ALBUM(id_album), 
	FOREIGN KEY (la_label)      references LABEL(id_label) 
	
)
