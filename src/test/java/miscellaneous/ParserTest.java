package miscellaneous;

import command.AddAppointmentCommand;
import command.Command;
import command.DeleteAppointmentCommand;
import command.ListAppointmentCommand;
import exception.InvalidInputFormatException;
import exception.UnknownCommandException;
import manager.Appointment;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import static manager.Appointment.INPUT_FORMAT;
import static miscellaneous.Parser.parse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


class ParserTest {

    @Test
    void parseAddAppointment_validInput_returnsAppointment() throws Exception {
        String input = "add-appointment ic/S1234567D dt/2025-09-20 t/1430 dsc/Checkup";

        Appointment appointment = Parser.parseAddAppointment(input);

        assertNotNull(appointment);
        assertEquals("S1234567D", appointment.getNric());
        assertEquals(LocalDateTime.parse("2025-09-20 1430", INPUT_FORMAT), appointment.getDateTime());
        assertEquals("Checkup", appointment.getDescription());
    }

    @Test
    void parseAddAppointment_invalidFormat_expectException() {
        String input = "add-appointment ic/S1234567D dt/2025-03-19 t/1200";

        assertThrows(InvalidInputFormatException.class, () -> Parser.parseAddAppointment(input));
    }

    @Test
    void parseAddAppointment_missingDetail_expectException() {
        String input = "add-appointment ic/ dt/2025-03-19 t/1200 dsc/medical check-up";

        assertThrows(InvalidInputFormatException.class, () -> Parser.parseAddAppointment(input));
    }

    @Test
    void parseAddAppointment_dateTimeInThePast_expectException() {
        String input2 = "add-appointment ic/S1234567D dt/2025-03-20 t/1300 dsc/Checkup";
        assertThrows(InvalidInputFormatException.class, () -> Parser.parseAddAppointment(input2));
    }

    @Test
    void parseAddAppointment_invalidDateTimeFormat_expectException() {
        String input1 = "add-appointment ic/S1234567D dt/03-19 t/1900 dsc/Checkup";
        assertThrows(InvalidInputFormatException.class, () -> Parser.parseAddAppointment(input1));

        String input2 = "add-appointment ic/S1234567D dt/2025-03-20 t/7:00PM dsc/Checkup";
        assertThrows(InvalidInputFormatException.class, () -> Parser.parseAddAppointment(input2));
    }

    @Test
    void parseDeleteAppointment_validInput_expectSuccess() throws InvalidInputFormatException {
        String input = "delete-appointment A100";
        String result = Parser.parseDeleteAppointment(input);

        assertEquals("A100", result, "Appointment ID does not match");
    }

    @Test
    void parseDeleteAppointment_extraSpaces_expectSuccess() throws InvalidInputFormatException {
        String input = "delete-appointment    A100";
        String result = Parser.parseDeleteAppointment(input);

        assertEquals("A100", result, "Appointment ID does not match");
    }

    @Test
    void parseDeleteAppointment_invalidAppointmentId_expectException() {
        String input = "delete-appointment 100";

        assertThrows(InvalidInputFormatException.class, () -> Parser.parseDeleteAppointment(input));
    }

    @Test
    void parseDeleteAppointment_lowercaseInput_expectSuccess() throws InvalidInputFormatException {
        String input = "delete-appointment a100";
        String result = Parser.parseDeleteAppointment(input);

        assertEquals("a100", result, "Appointment ID does not match");
    }

    @Test
    void parseAddPatient_invalidInputFormat_expectException() {
        String input = "add-patient n/John Doe ic/ dob/12-12-1999 g/M " +
                "p/98765432 a/123 Main Street h/Diabetes, Hypertension";

        assertThrows(InvalidInputFormatException.class, () -> Parser.parse(input));
    }

    @Test
    void parseViewHistory_validNric() throws InvalidInputFormatException {
        String[] result = Parser.parseViewHistory("view-history S1234567D");
        assertEquals("ic", result[0]);
        assertEquals("S1234567D", result[1]);
    }

    @Test
    void parseViewHistory_validName() throws InvalidInputFormatException {
        String[] result = Parser.parseViewHistory("view-history John Doe");
        assertEquals("n", result[0]);
        assertEquals("John Doe", result[1]);
    }

    @Test
    void parseViewHistory_explicitNricPrefix() throws InvalidInputFormatException {
        String[] result = Parser.parseViewHistory("view-history ic/S1234567A");
        assertEquals("ic", result[0]);
        assertEquals("S1234567A", result[1]);
    }

    @Test
    void parseViewHistory_invalidInput_expectException() {
        assertThrows(InvalidInputFormatException.class, () ->
                Parser.parseViewHistory("view-history"));
        assertThrows(InvalidInputFormatException.class, () ->
                Parser.parseViewHistory("view-history  "));
    }

    @Test
    void parseStoreHistory_validInput_expectSuccess() throws InvalidInputFormatException {
        String[] result = Parser.parseStoreHistory("store-history n/John Doe ic/S1234567D h/Allergic to nuts");
        assertEquals("John Doe", result[0]);
        assertEquals("S1234567D", result[1]);
        assertEquals("Allergic to nuts", result[2]);
    }

    @Test
    void parseStoreHistory_missingFields_expectException() {
        assertThrows(InvalidInputFormatException.class, () ->
                Parser.parseStoreHistory("store-history ic/S1234567D h/Allergic to nuts"));
        assertThrows(InvalidInputFormatException.class, () ->
                Parser.parseStoreHistory("store-history n/John Doe h/Allergic to nuts"));
        assertThrows(InvalidInputFormatException.class, () ->
                Parser.parseStoreHistory("store-history n/John Doe ic/S1234567D"));
}

    @Test
    void parse_addAppointmentCommand_expectAddAppointmentCommand() throws InvalidInputFormatException,
            UnknownCommandException {
        Command command = Parser.parse("add-appointment ic/S1234567D dt/2025-09-20 t/1900 dsc/Checkup");
        assertInstanceOf(AddAppointmentCommand.class, command);
    }

    @Test
    void parse_wrongFormatAddAppointment_expectInvalidInputFormatException() {
        String input = "add-appointment ic/ dt/ t/ dsc/";
        assertThrows(InvalidInputFormatException.class, () -> Parser.parse(input));
    }

    @Test
    void parse_deleteAppointmentCommand_expectDeleteAppointmentCommand() throws InvalidInputFormatException,
            UnknownCommandException {
        Command command = Parser.parse("delete-appointment A100");
        assertInstanceOf(DeleteAppointmentCommand.class, command);
    }

    @Test
    void parse_listAppointmentCommand_expectListAppointmentCommandCommand() throws Exception {
        Command command = Parser.parse("list-appointment");
        assertInstanceOf(ListAppointmentCommand.class, command);
    }

    @Test
    void parse_unknownCommand_expectUnknownCommandException() {
        String userInput = "bee-boo";
        assertThrows(UnknownCommandException.class, () -> parse(userInput));
    }

}
