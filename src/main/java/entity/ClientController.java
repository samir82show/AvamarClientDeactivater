/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "clientController")
@RequestScoped
public class ClientController {

    private String client;
    private List<String> clients;
    private String output;

    @PostConstruct
    public void init() {
        clients = new ArrayList<>();
    }

    public String getOutput() {
        return output;
    }

    public ClientController() {
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getClients() throws FileNotFoundException, IOException {
        int ch;
        BufferedReader bufferedReader = new BufferedReader(new FileReader("/home/payara/clients"));
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            clients.add(new Client(line).getName());
        }

        return new Gson().toJson(clients);

    }

    public void deactivateClient() {
        InputStream outputStream, errorStream = null;
        BufferedReader br = null;
        Process process;
        InputStreamReader outputISR, errorISR = null;
        String line, lines = "";
        try {
            process = new ProcessBuilder("ssh", "admin@SDTLAVUN01PRP", "/home/admin/deactivate_client.sh", client).start();
            process.waitFor();
            if (process.exitValue() == 0) {
                outputStream = process.getInputStream();
                outputISR = new InputStreamReader(outputStream);
                br = new BufferedReader(outputISR);
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("succeeded"));
                while ((line = br.readLine()) != null) {
                    lines += line;
                }
                output = lines;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(output));
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(client + " is deactivated!"));
            } else {
                errorStream = process.getErrorStream();
                errorISR = new InputStreamReader(errorStream);
                br = new BufferedReader(errorISR);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("failed"));
                while ((line = br.readLine()) != null) {
                    lines += line;
                }
                output = lines;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(output));
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(client + " deactivation error!"));
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientController.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (InterruptedException ex) {
            Logger.getLogger(ClientController.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
                errorISR.close();
                errorStream.close();

            } catch (Exception e) {
                Logger.getLogger(ClientController.class
                        .getName()).log(Level.SEVERE, null, e);
            }
        }
    }

}
