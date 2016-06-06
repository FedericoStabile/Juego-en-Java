
create table FILTER
(
	id_filter       numeric(10,0) identity (1,1),
	filter_nombre   nvarchar(255),


	PRIMARY KEY (id_filter)
)



create table ALBUM
(
	id_album          numeric(10,0) identity (1,1),
	album_path        nvarchar(255),
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




-------------- -----------        Linkin Park             ---------------------------------------------------------------------------
	insert into ALBUM(album_path,album_dicImagen,fecha_modificacion)
    values('c:carpeta1','c:carpeta2',getdate())

	
			insert into FILTER(filter_nombre)
    values('Titulo')
	
			insert into FILTER(filter_nombre)
    values('Autor')
	
			insert into FILTER(filter_nombre)
    values('Genero')
	
			insert into FILTER(filter_nombre)
    values('Año')


		insert into FILTER_ALBUM(fa_album,fa_filter)
    values(1,1)
	
		insert into FILTER_ALBUM(fa_album,fa_filter)
    values(1,2)
	
		insert into FILTER_ALBUM(fa_album,fa_filter)
    values(1,3)
	
		insert into FILTER_ALBUM(fa_album,fa_filter)
    values(1,4)


	insert into LABEL(label_nombre,filter)
    values('The Hunting Party',1)

		insert into LABEL(label_nombre,filter)
    values('Linkin Park',2)

		insert into LABEL(label_nombre,filter)
    values('Rock',3)

		insert into LABEL(label_nombre,filter)
    values('Altenative',3)

			insert into LABEL(label_nombre,filter)
    values('2012',4)


	insert into LABEL_ALBUM(la_album,la_label)
    values(1,1)
		insert into LABEL_ALBUM(la_album,la_label)
    values(1,2)
		insert into LABEL_ALBUM(la_album,la_label)
    values(1,3)
		insert into LABEL_ALBUM(la_album,la_label)
    values(1,4)
		insert into LABEL_ALBUM(la_album,la_label)
    values(1,5)
-------------- -----------        Linkin Park             ---------------------------------------------------------------------------


-------------- -----------        the beatles             ---------------------------------------------------------------------------


	insert into ALBUM(album_path,album_dicImagen,fecha_modificacion)
    values('c:carpeta55','c:carpeta44',getdate())
	



		insert into FILTER_ALBUM(fa_album,fa_filter)
    values(2,1)
	
		insert into FILTER_ALBUM(fa_album,fa_filter)
    values(2,2)
	
		insert into FILTER_ALBUM(fa_album,fa_filter)
    values(2,3)
	
		insert into FILTER_ALBUM(fa_album,fa_filter)
    values(2,4)


	insert into LABEL(label_nombre,filter)
    values('Yellow Submarine',1)

		insert into LABEL(label_nombre,filter)
    values('Beatles',2)


		insert into LABEL(label_nombre,filter)
    values('Clasico',3)

			insert into LABEL(label_nombre,filter)
    values('2012',4)


	insert into LABEL_ALBUM(la_album,la_label)
    values(2,6)
		insert into LABEL_ALBUM(la_album,la_label)
    values(2,7)
		insert into LABEL_ALBUM(la_album,la_label)
    values(2,8)
		insert into LABEL_ALBUM(la_album,la_label)
    values(2,9)
	
			insert into LABEL_ALBUM(la_album,la_label)
    values(2,3)

	
	select * from LABEL
inner join  LABEL_ALBUM on id_label= la_label

select * from LABEL
inner join FILTER on id_filter= filter


 --se le elijio un filtro cualquiera, por ejemplo "genero",
 -- entonces mostramos todos los generos que hay
 select label_nombre from LABEL
inner join FILTER on id_filter= filter
 where filter_nombre = 'Genero'


-- se elijio rock, entonces mostramos todos los albunes con lebel rock
select *  
from LABEL
 inner join  LABEL_ALBUM on id_label= la_label
 inner join ALBUM on id_album = la_album
 where label_nombre = 'Rock'
 
 


-- seter de ALBUM. Devuelve su id correspondiente (@id_album)
create procedure st_insert_ALBUM
@album_path nvarchar(255),
@album_dicImagen nvarchar(255),
@fecha_modificacion datetime,
@id_album numeric(10,0) output
as
begin

    insert into ALBUM (album_path,album_dicImagen,fecha_modificacion)
	values (@album_path, @album_dicImagen,@fecha_modificacion)
	set @id_album = scope_identity()
    
end

--seter FILTER. Devuelve su id correspondiente (@id_filter)
create procedure st_insert_FILTER
@filter_nombre nvarchar(255),
@id_filter numeric(10,0) output
as
begin

    insert into FILTER (filter_nombre)
	values (@filter_nombre)
	set @id_filter = scope_identity()
    
end

--seter LABEL, @filter el el id_filter al cual pertenece. Devuelve su id correspondiente (@id_label)
create procedure st_insert_LABEL
@label_nombre nvarchar(255),
@filter numeric(10,0),
@id_label numeric(10,0) output
as
begin

    insert into FILTER (label_nombre,filter)
	values (@label_nombre,@filter)
	set @id_label = scope_identity()
    
end



