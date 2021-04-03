import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.*;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.*;

public class SheetsQuickstart {

    private static Sheets sheetsServices;
    private static String APPLICATION_NAME = "Google sheets";
    private static String SPREADSHEET_ID = "1E139cCnMxIre7qi6AIyMzfymL7rWTtHOspzhcXPj4AM";

    private static Credential authorize() throws IOException, GeneralSecurityException{
      InputStream in = SheetsQuickstart.class.getResourceAsStream("/credentials.json");
      GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
              JacksonFactory.getDefaultInstance(), new InputStreamReader(in)
      );
      List<String> scopes =  Arrays.asList(SheetsScopes.SPREADSHEETS);

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(),
                clientSecrets, scopes)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File("tokens")))
                .setAccessType("offline")
                .build();
        Credential credential = new AuthorizationCodeInstalledApp(
        flow, new LocalServerReceiver()).authorize("user");
        return credential;
    }

    public static Sheets getSheetsServices() throws IOException, GeneralSecurityException{
        Credential credential = authorize();
        return new Sheets.Builder(GoogleNetHttpTransport.newTrustedTransport(),
                JacksonFactory.getDefaultInstance(), credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public static void main(String[] args) throws IOException, GeneralSecurityException{
        sheetsServices = getSheetsServices();


        String range = "Produtos!A1:C9";


        ValueRange response = sheetsServices.spreadsheets().values()
                .get(SPREADSHEET_ID, range)
                .execute();

        List<List<Object>> values = response.getValues();

        if(values == null || values.isEmpty()){
            System.out.println("Sem dados");
        }else {
            for (List row : values){
                System.out.printf("%s, %s, %s\n", row.get(0), row.get(1), row.get(2));
            }
        }

        /*
         * Inserir
         */
//        ValueRange addFields = new ValueRange()
//                .setValues(Arrays.asList(
//                        Arrays.asList(23, "Pastes", 2, 3.99, "Aguardando pagamento")
//                ));
//        AppendValuesResponse appendValues = sheetsServices.spreadsheets().values()
//                .append(SPREADSHEET_ID, "Pedidos", addFields)
//                .setValueInputOption("USER_ENTERED")
//                .setInsertDataOption("INSERT_ROWS")
//                .setIncludeValuesInResponse(true)
//                .execute();


        /*
         * Atualizar
         */
        ValueRange update = new ValueRange()
                .setValues(Arrays.asList(
                        Arrays.asList("Pago")
                ));
        UpdateValuesResponse result = sheetsServices.spreadsheets().values()
                .update(SPREADSHEET_ID, "E3", update)
                .setValueInputOption("RAW")
                .setIncludeValuesInResponse(true)
                .execute();


    }
}