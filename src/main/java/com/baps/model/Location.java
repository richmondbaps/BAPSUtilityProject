package com.baps.model;

/**
 * The Location POJO class which will be converted to JSON to return the result.
 * 
 * @author Mayuresh Trivedi
 */
public class Location
{
    private String address;

    private String serviceused;

    private String longitude;

    private String latitude;

    private String lookupdatetime;

    private String city;

    private String country;

    public String getAddress ()
    {
        return address;
    }

    public void setAddress (String address)
    {
        this.address = address;
    }

    public String getServiceused ()
    {
        return serviceused;
    }

    public void setServiceused (String serviceused)
    {
        this.serviceused = serviceused;
    }

    public String getLongitude ()
    {
        return longitude;
    }

    public void setLongitude (String longitude)
    {
        this.longitude = longitude;
    }

    public String getLatitude ()
    {
        return latitude;
    }

    public void setLatitude (String latitude)
    {
        this.latitude = latitude;
    }

    public String getLookupdatetime ()
    {
        return lookupdatetime;
    }

    public void setLookupdatetime (String lookupdatetime)
    {
        this.lookupdatetime = lookupdatetime;
    }

    public String getCity ()
    {
        return city;
    }

    public void setCity (String city)
    {
        this.city = city;
    }

    public String getCountry ()
    {
        return country;
    }

    public void setCountry (String country)
    {
        this.country = country;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [address = "+address+", serviceused = "+serviceused+", longitude = "+longitude+", latitude = "+latitude+", lookupdatetime = "+lookupdatetime+", city = "+city+", country = "+country+"]";
    }
}
