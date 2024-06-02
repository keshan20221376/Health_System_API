package com.example.resource;

import com.example.dao.BillingDAO;
import com.example.model.Billing;
import com.example.exception.UserNotFoundException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/billings")
public class BillingResource {

    private static final Logger LOGGER = Logger.getLogger(BillingResource.class.getName());
    private BillingDAO billingDAO = new BillingDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBillings() {
        try {
            List<Billing> billings = billingDAO.get_all_billings();
            LOGGER.log(Level.INFO, "Retrieved all billings successfully");
            return Response.ok().entity(billings).build();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Failed to fetch billings", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to fetch the billings. Please try again.").build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBillingById(@PathParam("id") int id) {
        try {
            Billing billing = billingDAO.get_the_billing(id);
            if (billing != null) {
                LOGGER.log(Level.INFO, "Retrieved billing with ID {0} successfully", id);
                return Response.ok().entity(billing).build();
            } else {
                throw new UserNotFoundException("Billing with ID " + id + " not found.");
            }
        } catch (UserNotFoundException ex) {
            LOGGER.log(Level.WARNING, "Billing with ID {0} not found", id);
            return Response.status(Response.Status.NOT_FOUND).entity(ex.getMessage()).build();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Failed to fetch billing with ID " + id, ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to fetch billing with ID " + id + ". Please try again later.").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBilling(Billing billing) {
        try {
            billingDAO.add_the_billing(billing);
            LOGGER.log(Level.INFO, "Added billing successfully");
            return Response.status(Response.Status.CREATED).entity("Billing added successfully.").build();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Failed to add billing", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to add billing. Please try again later.").build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBilling(@PathParam("id") int billingId, Billing updatedBilling) {
        try {
            Billing existingBilling = billingDAO.get_the_billing(billingId);
            if (existingBilling != null) {
                updatedBilling.set_no(billingId);
                billingDAO.update_the_billing(updatedBilling);
                LOGGER.log(Level.INFO, "Updated billing with ID {0} successfully", billingId);
                return Response.ok().entity("Billing with ID " + billingId + " successfully Updated").build();
            } else {
                throw new UserNotFoundException("Billing with ID " + billingId + " not found.");
            }
        } catch (UserNotFoundException ex) {
            LOGGER.log(Level.WARNING, "Billing with ID {0} not found", billingId);
            return Response.status(Response.Status.NOT_FOUND).entity(ex.getMessage()).build();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Failed to update billing with ID " + billingId, ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to update billing with ID " + billingId + ". Please try again later.").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBilling(@PathParam("id") int id) {
        try {
            billingDAO.deleting_bills(id);
            LOGGER.log(Level.INFO, "Deleted billing with ID {0} successfully", id);
            return Response.ok().entity("Billing with ID " + id + " successfully deleted.").build();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Failed to delete billing with ID " + id, ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to delete billing with ID " + id + ". Error: " + ex.getMessage()).build();
        }
    }
}
