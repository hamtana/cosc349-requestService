package web;

import dao.PropertyDAO;
import domain.Property;
import io.jooby.Jooby;
import io.jooby.StatusCode;

import java.util.Collection;

public class PropertyModule extends Jooby {

   public PropertyModule(PropertyDAO dao){

       //Retrieve properties by tenant
       get("/api/properties/tenant/{username}", ctx -> {
           String username = ctx.path("username").value();
           Property property = dao.getPropertyByTenantUsername(username);
           if(property == null){
                return ctx.send("No property found for tenant: " + username);
              } else {
                return property;
           }
       });

       //Retrieve properties by manager
       get("/api/properties/manager/{username}", ctx -> {
           String username = ctx.path("username").value();
           Collection<Property> properties = dao.getPropertiesByManagerUsername(username);
                if(properties.isEmpty()){
                    return ctx.send("No properties found for manager: " + username);
                } else {
//                    testing
//                    ArrayList<Property> propertiesArr = new ArrayList<Property>(properties);
//                    System.out.println(propertiesArr.get(0).getTenant().getUsername());
                    return properties;

                }
       });

       put("/api/properties/{address}", ctx -> {
             Property property = ctx.body().to(Property.class);
             dao.updateProperty(property);
             return ctx.send(StatusCode.OK);
       });

       delete("/api/properties/{address}", ctx -> {
             String address = ctx.path("address").value();
             Property property = dao.getPropertyByAddress(address);
             dao.deleteProperty(property);
             return ctx.send(StatusCode.OK);
       });

       post("/api/properties", ctx -> {
             Property property = ctx.body().to(Property.class);
             dao.createProperty(property);
             return ctx.send(StatusCode.CREATED);
       });



   }
}
