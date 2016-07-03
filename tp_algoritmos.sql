
create procedure st_buscar_filter
@nombre_filter nvarchar(255),
@id_filter numeric(10,0) out 
AS
begin
select @id_filter=id_filter from FILTER
where filter_nombre = @nombre_filter
end



create procedure st_todos_filters
AS
begin
select * from FILTER
end





create procedure st_buscar_filters_album
@id_album numeric(10,0)
AS
begin
select filter_nombre from FILTER_ALBUM
inner join Filter on id_filter=fa_filter
where @id_album = fa_album
end




 --si esta en la tabla de filter te devuelve  @bool =1 y @id_filter el id corespondiente, si no esta @bool = 0 y @id_filter es basura
 create procedure st_estasEnLaTabla_Filter
 @nombre nvarchar(255),
 @bool bit out,
 @id_filter numeric(10,0) out
as
begin
declare @cant int

      select @cant= count(*),@id_filter = id_filter
	  from FILTER
	  where filter_nombre =@nombre
	  group by id_filter

	  if (@cant <> 0)
	   SET @bool=1
      ELSE
	   SET @bool=0


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



------------------------------------LABEL----------------------------------------------------------------------------------
create procedure st_buscar_Label
@nombre_label nvarchar(255),
@id_label numeric(10,0)
AS
begin
select @id_label= id_label from LABEL
where label_nombre = @id_label
end




create procedure st_todos_Labels
AS
begin
select label_nombre from LABEL
end



create procedure st_todos_Labels_de_un_filter
@filter_nombre nvarchar(255)
AS
begin
select label_nombre
from LABEL
inner join  FILTER on filter = id_filter
where filter_nombre= @filter_nombre

end

 --si esta en la tabla de Label te devuelve  @bool =1 y @id_label el id corespondiente, si no esta @bool = 0 y @id_label es basura
 create procedure st_estasEnLaTabla_Label
 @nombre nvarchar(255),
 @bool bit out,
 @id_label numeric(10,0) out
as
begin
declare @cant int

      select @cant= count(*),@id_label = id_label
	  from LABEL
	  where label_nombre =@nombre
	  group by id_label

	  if (@cant <> 0)
	   SET @bool=1
      ELSE
	   SET @bool=0

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



---------------------------------------album----------------------------------------------------------------

-- seter de ALBUM. Devuelve su id correspondiente (@id_album)
create procedure st_insert_Album
@path nvarchar(255),
@id_album numeric(10,0) output
as
begin

    insert into ALBUM (album_path)
	values (@path)
	set @id_album = scope_identity()
    
end


create procedure st_getPath

AS
begin

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

-------------------------------------------otros--------------------------------------------------------------------

create procedure st_formatearTablas
as
begin

delete FILTER_ALBUM
delete LABEL_ALBUM
delete ALBUM
delete LABEL
    
end
