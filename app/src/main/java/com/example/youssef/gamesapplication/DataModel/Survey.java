
package com.example.youssef.gamesapplication.DataModel;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.persistence.*;
import com.backendless.geo.GeoPoint;

import java.util.List;
import java.util.Date;

public class Survey
{
  private String userName;
  private String ownerId;
  private String userImage;
  private String review;
  private String objectId;
  private Date updated;
  private Date created;
  private Integer rate;
  public String getUserName()
  {
    return userName;
  }

  public void setUserName( String userName )
  {
    this.userName = userName;
  }

  public String getOwnerId()
  {
    return ownerId;
  }

  public String getUserImage()
  {
    return userImage;
  }

  public void setUserImage( String userImage )
  {
    this.userImage = userImage;
  }

  public String getReview()
  {
    return review;
  }

  public void setReview( String review )
  {
    this.review = review;
  }

  public String getObjectId()
  {
    return objectId;
  }

  public Date getUpdated()
  {
    return updated;
  }

  public Date getCreated()
  {
    return created;
  }

  public Integer getRate()
  {
    return rate;
  }

  public void setRate( Integer rate )
  {
    this.rate = rate;
  }

                                                    
  public Survey save()
  {
    return Backendless.Data.of( Survey.class ).save( this );
  }

  public void saveAsync( AsyncCallback<Survey> callback )
  {
    Backendless.Data.of( Survey.class ).save( this, callback );
  }

  public Long remove()
  {
    return Backendless.Data.of( Survey.class ).remove( this );
  }

  public void removeAsync( AsyncCallback<Long> callback )
  {
    Backendless.Data.of( Survey.class ).remove( this, callback );
  }

  public static Survey findById( String id )
  {
    return Backendless.Data.of( Survey.class ).findById( id );
  }

  public static void findByIdAsync( String id, AsyncCallback<Survey> callback )
  {
    Backendless.Data.of( Survey.class ).findById( id, callback );
  }

  public static Survey findFirst()
  {
    return Backendless.Data.of( Survey.class ).findFirst();
  }

  public static void findFirstAsync( AsyncCallback<Survey> callback )
  {
    Backendless.Data.of( Survey.class ).findFirst( callback );
  }

  public static Survey findLast()
  {
    return Backendless.Data.of( Survey.class ).findLast();
  }

  public static void findLastAsync( AsyncCallback<Survey> callback )
  {
    Backendless.Data.of( Survey.class ).findLast( callback );
  }

  public static List<Survey> find( DataQueryBuilder queryBuilder )
  {
    return Backendless.Data.of( Survey.class ).find( queryBuilder );
  }

  public static void findAsync( DataQueryBuilder queryBuilder, AsyncCallback<List<Survey>> callback )
  {
    Backendless.Data.of( Survey.class ).find( queryBuilder, callback );
  }
}