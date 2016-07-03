create procedure st_buscar_filter
@nombre_filter nvarchar(255),
@id_filter numeric(10,0) out 
AS
begin
select @id_filter=id_filter from FILTER
where filter_nombre = @nombre_filter
end

exec st_buscar_filter Genero

create procedure st_todos_filters
AS
begin
select * from FILTER
end


exec st_todos_filters


create procedure st_buscar_filters_album
@id_album numeric(10,0)
AS
begin
select filter_nombre from FILTER_ALBUM
inner join Filter on id_filter=fa_filter
where @id_album = fa_album
end

exec st_buscar_filter_album 2
------------------------------------LABEL----------------------------------------------------------------------------------
create procedure st_buscar_Label
@nombre_label nvarchar(255),
@id_label numeric(10,0)
AS
begin
select @id_label= id_label from LABEL
where label_nombre = @id_label
end


exec st_buscar_Label


create procedure st_todos_Labels
AS
begin
select label_nombre from LABEL
end

exec st_todos_Labels


create procedure st_todos_Labels_de_un_filter
@filter_nombre nvarchar(255)
AS
begin
select label_nombre
from LABEL
inner join  FILTER on filter = id_filter
where filter_nombre= @filter_nombre

end

---------------------------------------album----------------------------------------------------------------

create procedure st_insert_Album
@path nvarchar(255),
@id_album numeric(10,0) output
as
begin

    insert into ALBUM (album_path)
	values (@path)
	set @id_album = scope_identity()
    
end


---------------------------------------FILTER/ALBUM----------------------------------------------------------------
create procedure st_insert_FilterAlbum
@id_album numeric(10,0),
@id_filter numeric(10,0)
as
begin

    insert into FILTER_ALBUM(fa_album,fa_filter)
	values (@id_album,@id_filter)
    
end

---------------------------------------LABEL/ALBUM----------------------------------------------------------------

create procedure st_insert_LabelAlbum
@id_album numeric(10,0),
@id_label numeric(10,0)
as
begin

    insert into LABEL_ALBUM(la_album,la_label)
	values (@id_album,@id_label)
    
end
