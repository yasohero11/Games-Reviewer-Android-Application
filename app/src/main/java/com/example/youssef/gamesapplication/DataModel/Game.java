
package com.example.youssef.gamesapplication.DataModel;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.persistence.*;
import com.backendless.geo.GeoPoint;

import java.util.List;
import java.util.Date;

public class Game
{
  private Date updated;
  private Date created;
  private String logo;
  private String price;
  private String name;
  private String objectId;
  private String ownerId;

  public Game() {
  }

  public Date getUpdated()
  {
    return updated;
  }

  public Date getCreated()
  {
    return created;
  }

  public String getLogo()
  {
    return logo;
  }

  public void setLogo( String logo )
  {
    this.logo = logo;
  }

  public String getPrice()
  {
    return price;
  }

  public void setPrice( String price )
  {
    this.price = price;
  }

  public String getName()
  {
    return name;
  }

  public void setName( String name )
  {
    this.name = name;
  }

  public String getObjectId()
  {
    return objectId;
  }

  public String getOwnerId()
  {
    return ownerId;
  }

                                                    
  public Game save()
  {
    return Backendless.Data.of( Game.class ).save( this );
  }

  public void saveAsync( AsyncCallback<Game> callback )
  {
    Backendless.Data.of( Game.class ).save( this, callback );
  }

  public Long remove()
  {
    return Backendless.Data.of( Game.class ).remove( this );
  }

  public void removeAsync( AsyncCallback<Long> callback )
  {
    Backendless.Data.of( Game.class ).remove( this, callback );
  }

  public static Game findById( String id )
  {
    return Backendless.Data.of( Game.class ).findById( id );
  }

  public static void findByIdAsync( String id, AsyncCallback<Game> callback )
  {
    Backendless.Data.of( Game.class ).findById( id, callback );
  }

  public static Game findFirst()
  {
    return Backendless.Data.of( Game.class ).findFirst();
  }

  public static void findFirstAsync( AsyncCallback<Game> callback )
  {
    Backendless.Data.of( Game.class ).findFirst( callback );
  }

  public static Game findLast()
  {
    return Backendless.Data.of( Game.class ).findLast();
  }

  public static void findLastAsync( AsyncCallback<Game> callback )
  {
    Backendless.Data.of( Game.class ).findLast( callback );
  }

  public static List<Game> find( DataQueryBuilder queryBuilder )
  {
    return Backendless.Data.of( Game.class ).find( queryBuilder );
  }

  public static void findAsync( DataQueryBuilder queryBuilder, AsyncCallback<List<Game>> callback )
  {
    Backendless.Data.of( Game.class ).find( queryBuilder, callback );
  }
}