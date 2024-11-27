/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pacote;

import java.awt.AWTException;
import java.awt.CheckboxMenuItem;
import java.awt.HeadlessException;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Felipe Campos
 */
public class server {
    Menu mnuActions;
    CheckboxMenuItem itemStart;
    MenuItem itemStop;
    MenuItem itemAbout;
    PopupMenu popup;
    TrayIcon trayicon;
    SystemTray systemtray;
    
    public server(){
        try{
            if(!SystemTray.isSupported()){
                System.out.println("Sem suporte a SystemTray");
                return;
            }
            
            Registry reg = LocateRegistry.createRegistry(6666);
            rmi_interface objRmi = (rmi_interface) new rmi_implementation();
            
            mnuActions = new Menu("Ações");
            itemStart = new CheckboxMenuItem("On/Off");
            itemStop = new MenuItem("Parar");
            itemAbout = new MenuItem("Sobre");
            
            mnuActions.add(itemStart);
            mnuActions.add(itemStop);
            
            popup = new PopupMenu();
            popup.add(mnuActions);
            popup.addSeparator();
            popup.add(itemAbout);

            ImageIcon icon = new ImageIcon("images/server.png");
            trayicon = new TrayIcon(icon.getImage());
            trayicon.setPopupMenu(popup);
            systemtray = SystemTray.getSystemTray();
            
            itemStart.addItemListener(new ItemListener(){
                @Override
                public void itemStateChanged(ItemEvent e) {
                    try{
                        if(e.getStateChange() == 1){
                            reg.rebind("servidor", objRmi);
                            JOptionPane.showMessageDialog(null, "Servidor iniciado");
                        } else {
                            reg.unbind("servidor");
                            UnicastRemoteObject.unexportObject(objRmi, true);
                            JOptionPane.showMessageDialog(null, "Servidor parado");
                        }
                    }
                    catch (Exception error){
                        JOptionPane.showMessageDialog(null, "Erro: " + error.getMessage());
                    }
                }
                
            });
            
            try{
                systemtray.add(trayicon);
            } 
            catch (AWTException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            }
        } catch (HeadlessException | RemoteException e){
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
    }
}
