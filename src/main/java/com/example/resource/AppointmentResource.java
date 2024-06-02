package com.example.resource;

import com.example.dao.AppointmentDAO;
import com.example.model.Appointment;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/appointments")
public class AppointmentResource {

    private static final Logger LOGGER = Logger.getLogger(AppointmentResource.class.getName());
    private AppointmentDAO appointmentDAO = new AppointmentDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAppointments() {
        try {
            List<Appointment> appointments = appointmentDAO.trigger_all_patients();
            LOGGER.log(Level.INFO, "successfully retrieved every appointment");
            return Response.ok().entity(appointments).build();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Failed to fetch appointments", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("did not get appointments. Please give it another go later.").build();
        }
    }

    @GET
    @Path("/{appointmentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAppointmentById(@PathParam("appointmentId") int appointmentId) {
        try {
            Appointment appointment = appointmentDAO.get_the_AppointmentBy_Id(appointmentId);
            if (appointment != null) {
                LOGGER.log(Level.INFO, "get appointment with ID: " + appointmentId + " successfully");
                return Response.ok().entity(appointment).build();
            } else {
                LOGGER.log(Level.INFO, "Appointment with ID: " + appointmentId + " not found");
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Appointment with ID: " + appointmentId + " not found").build();
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Failed to fetch appointment with ID: " + appointmentId, ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Not able to retrieve appointment with ID:" + appointmentId + ". Please try again later.").build();
        }
    }

    @POST
    public Response addAppointment(Appointment appointment) {
        try {
            appointmentDAO.add_the_Appointment(appointment);
            LOGGER.log(Level.INFO, "Added new appointment successfully");
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "neglected to add an appointment", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("neglected to add the appointment. Please give it another go later.").build();
        }
    }

    @PUT
    @Path("/{appointmentId}")
    public Response updateAppointment(@PathParam("appointmentId") int appointmentId, Appointment updatedAppointment) {
        try {
            appointmentDAO.update_Appointment(appointmentId, updatedAppointment);
            LOGGER.log(Level.INFO, "Updated appointment with ID: " + appointmentId + " successfully");
            return Response.ok().build();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Failed to update appointment with ID: " + appointmentId, ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Not updating the appointment using the ID: " + appointmentId + ". Please try again later.").build();
        }
    }

    @DELETE
    @Path("/{appointmentId}")
    public Response deleteAppointment(@PathParam("appointmentId") int appointmentId) {
        try {
            appointmentDAO.deleteAppointment(appointmentId);
            LOGGER.log(Level.INFO, "Appointment deleted with ID: " + appointmentId + " successfully");
            return Response.ok().entity("Appointment with ID " + appointmentId + " successfully deleted.").build();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Not able to remove the appointment using ID: " + appointmentId, ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Attempt to remove appointment using ID failed: " + appointmentId + ". Please try again later.").build();
        }
    }
}
