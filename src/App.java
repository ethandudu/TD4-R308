import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;
public class App {

    private String serveur;

    public App (String serveur) {
        this.serveur= serveur;
    }

    public URLConnection ouvrirURL (String s) {
        URLConnection c;
        URL u;

        c= null;
        u= null;

        try {
            u= new URL(serveur + "?" + s);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            c= u.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return c;
    }

    public String lireURL (String s) {
        BufferedReader in;
        String inpuline;
        StringBuilder texte;
        URLConnection c;

        c= ouvrirURL(s);

        texte= new StringBuilder();
        try {
            in= new BufferedReader(new InputStreamReader(c.getInputStream(), "UTF-8"));
            while ((inpuline= in.readLine()) != null) {
                texte.append(inpuline + "\n");
            }
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return texte.toString();
    }

    public static void main(String[] args) throws Exception {
        Scanner s;
        s = null;
        try {
            App p;
            String lu;

            p= new App("http://infort.gautero.fr");

            s = new Scanner(System.in);
            System.out.println("Bienvenue, merci de taper votre choix parmi la sélection suivante :");
            System.out.println("1 - Liste des BUT\n2 - Liste des UE d'un BUT\n3 - Détail d'une UE\n4 - Liste des ressources d'une UE\n7 - Informations sur une ressource\n6 - Liste des SAE d'une UE\n7 - Infos sur une SAE");

            while (s.hasNextLine()){
                int choix = s.nextInt();
                if (choix == 1){
                    lu = p.lireURL("action=get&obj=but");
                    String jsonString = lu;
                    JSONArray jsonArray = new JSONArray(jsonString);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject explrObject = jsonArray.getJSONObject(i);
                        System.out.println("ID : " + explrObject.get("id") + " Specialité : " + explrObject.get("specialite"));
                    }
                } else if (choix == 2) {
                    System.out.println("ID du BUT : ");
                    int idBut = s.nextInt();
                    String url = "action=get&obj=ue&idBut=" + idBut;
                    lu = p.lireURL(url);
                    String jsonString = lu;
                    JSONArray jsonArray = new JSONArray(jsonString);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject explrObject = jsonArray.getJSONObject(i);
                        String parcours = explrObject.get("parcours").toString();
                        if (parcours=="null"){
                            parcours = "Tronc commun";
                        }
                        System.out.println("ID : " + explrObject.get("id") + " Semestre : " +explrObject.get("semestre") + " Parcours : " + parcours);
                    }
                } else if (choix == 3){
                    System.out.println("ID du BUT :");
                    int idBut = s.nextInt();
                    System.out.println("ID l'UE :");
                    int idUE = s.nextInt();
                    String url = "action=get&obj=ue&idBut="+idBut+"&idUe="+idUE;
                    lu = p.lireURL(url);
                    String jsonString = lu;
                    JSONArray jsonArray = new JSONArray(jsonString);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject explrObject = jsonArray.getJSONObject(i);
                        String parcours = explrObject.get("parcours").toString();
                        if (parcours == "null"){
                            parcours = "Tronc commun";
                        }
                        System.out.println("ID : " + explrObject.get("id") + " Semestre : " +explrObject.get("semestre") + " Parcours : " + parcours);
                    }
                } else if (choix == 4){
                    System.out.println("ID de l'UE :");
                    int idUe = s.nextInt();
                    String url = "action=get&obj=res&idUe="+idUe;
                    lu = p.lireURL(url);
                    String jsonString = lu;
                    JSONArray jsonArray = new JSONArray(jsonString);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject explrObject = jsonArray.getJSONObject(i);
                        System.out.println("ID : " + explrObject.get("id") + " Nom : " +explrObject.get("nom") + " Description : " +explrObject.get("description") +" Cours : " + explrObject.get("cours") + " TD : " + explrObject.get("td") + " TP : " + explrObject.get("tp"));
                    }
                } else if (choix == 5){
                    System.out.println("ID de la ressource :");
                    int idRes = s.nextInt();
                    String url = "action=get&obj=res&idRes="+idRes;
                    lu = p.lireURL(url);
                    String jsonString = lu;
                    JSONArray jsonArray = new JSONArray(jsonString);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject explrObject = jsonArray.getJSONObject(i);
                        System.out.println("ID : " + explrObject.get("id") + " Nom : " +explrObject.get("nom") + " Description : " +explrObject.get("description") +" Cours : " + explrObject.get("cours") + " TD : " + explrObject.get("td") + " TP : " + explrObject.get("tp"));
                    }
                } else if (choix == 6){
                    System.out.println("ID de l'UE :");
                    int idUe = s.nextInt();
                    String url = "action=get&obj=sae&idUe="+idUe;
                    lu = p.lireURL(url);
                    String jsonString = lu;
                    JSONArray jsonArray = new JSONArray(jsonString);
                    for (int i = 0; i < jsonArray.length(); i++){
                        JSONObject explrObject = jsonArray.getJSONObject(i);
                        System.out.println("ID : " + explrObject.get("id") + " Nom : " +explrObject.get("nom") + " Cours : " + explrObject.get("cours") + " TD : " + explrObject.get("td") + " TP : " + explrObject.get("tp") + " Projet : " + explrObject.get("projet"));
                    }
                } else if (choix == 7){
                    System.out.println("ID de la SAE :");
                    int idSae = s.nextInt();
                    String url = "action=get&obj=sae&idSae="+idSae;
                    lu = p.lireURL(url);
                    String jsonString = lu;
                    JSONArray jsonArray = new JSONArray(jsonString);
                    for (int i = 0; i < jsonArray.length(); i++){
                        JSONObject explrObject = jsonArray.getJSONObject(i);
                        System.out.println("ID : " + explrObject.get("id") + " Nom : " +explrObject.get("nom") + " Description : " + explrObject.get("description") + " Cours : " + explrObject.get("cours") + " TD : " + explrObject.get("td") + " TP : " + explrObject.get("tp") + " Projet : " + explrObject.get("projet"));
                    }
                }
            }
        } finally {
            s.close();
        }


    }
}
