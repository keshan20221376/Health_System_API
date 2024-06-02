package com.example.resource;

import com.example.dao.PrescriptionDAO;
import com.example.model.Prescription;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/prescriptions")

public class PrescriptionResource {

    private static final Logger LOGGER = Logger.getLogger(PrescriptionResource.class.getName());
    
    private PrescriptionDAO prescriptionDAO = new PrescriptionDAO();


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Prescription> getAllPrescriptions() {
        return prescriptionDAO.get_all_prescriptions();
    }
    
    
    @GET
    @Path("/{patientId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPrescription(@PathParam("patientId") int id) {
        try {
            Prescription prescription = prescriptionDAO.get_prescription(id);
            if (prescription != null) {
                LOGGER.log(Level.INFO, "Retrieved prescription for patient ID: " + id + " successfully");
                return Response.ok().entity(prescription).build();
            } else {
                LOGGER.log(Level.INFO, "No prescription found for patient ID: " + id);
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("No prescription found for patient ID: " + id).build();
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Failed to fetch prescription for patient ID: " + id, ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to fetch prescription for patient ID: " + id + ". Please try again later.").build();
        }
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPrescription(Prescription prescription) {
        try {
            prescriptionDAO.add_prescription(prescription);
            LOGGER.log(Level.INFO, "Added new prescription for patient ID: " + prescription.getPatient().getID() + " successfully");
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Failed to add prescription", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to add prescription. Please try again later.").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteStudent(@PathParam("id") int id) {
        try {
            prescriptionDAO.delete_prescription(id);
            LOGGER.log(Level.INFO, "Deleted prescription with ID: " + id + " successfully");
            return Response.ok().build();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Failed to delete prescription with ID: " + id, ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to delete prescription with ID: " + id + ". Please try again later.").build();
        }
    }
}

