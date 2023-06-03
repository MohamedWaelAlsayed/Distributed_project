/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package encyclopedia;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class EncyclopediaServer {
    public static void main(String[] args) {
        try {
            String data = "Healthcare is crucial for maintaining a healthy population It encompasses various aspects such as prevention diagnosis treatment and management of illnesses and diseases Access to quality healthcare services ensures individuals receive necessary medical care and attention Effective healthcare systems prioritize patient safety provide comprehensive care and address the diverse needs of the population Healthcare professionals play a vital role in delivering healthcare services and improving public health\n" +
            "Collaboration among healthcare providers policymakers and researchers promotes innovation and advancements in healthcare Investments in healthcare infrastructure and technology enable efficient healthcare delivery and better patient outcomes Health education and awareness campaigns empower individuals to make informed decisions about their health Public health initiatives focus on disease prevention health promotion and community well-being By prioritizing healthcare societies can enhance overall quality of life and promote healthier futures\n" +
            "For a people to remain healthy healthcare is essential It covers a wide range of topics including disease management treatment diagnosis and prevention People obtain the essential medical treatment and attention if they have access to high-quality healthcare services Systems of healthcare that are effective place a high priority on patient safety offer all-encompassing treatment and cater to the various requirements of the populace In providing healthcare services and enhancing public health healthcare workers are essential\n" +
            "Innovation and improvements in healthcare are facilitated through collaboration between healthcare professionals decision-makers and researchers Better patient outcomes are made possible by investments in healthcare infrastructure and technology Campaigns for health education and awareness enable people to make wise decisions regarding their health Disease prevention health promotion and community well-being are the main goals of public health efforts Societies can benefit by giving healthcare priority\n" +
            "Efficient healthcare systems which also offer comprehensive treatment and take into account the population's various demands Healthcare workers are essential to providing healthcare and enhancing public health Collaboration between";

            // Create an instance of the implementation class
            Encyclopedia encyclopedia = new EncyclopediaImpl(data);
            // Create a registry and bind the remote object to it
            Registry registry = LocateRegistry.createRegistry(4000);
            // Bind the remote object instance to a name in the RMI registry
            Naming.rebind("Encyclopedia", (Remote) encyclopedia);

            System.out.println("Server ready.");
        } catch (MalformedURLException | RemoteException e) {
            System.err.println("Server exception: " + e.toString());
        }
    }
}