package tietokone_paketti;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class Tietokone_Paketti {

    public static void main(String[] args) throws SQLException {

    // Tässä luodaan yhteys tietokantaan
        
        Scanner lukija = new Scanner (System.in);
        
        Connection conn = null;
        Properties connectionProps = new Properties();
        Boolean running = true;
        
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tietokone_paketit", "root", "");
        System.out.println("Successfully connected to database!");
        
    // Tässä kysytään mitä toimintoa käyttäjä haluaa käyttää
        
        do {
            ui();
            int valinta = Integer.valueOf(lukija.nextLine());
            System.out.println("");
            switch (valinta) {
                case 1:
                    nayta_tuotteet(conn, lukija);
                    break;
                case 2:
                    lisaa_tuote(conn, lukija);
                    break;
                case 3:
                    muuta_tuote(conn, lukija);
                    break;
                case 4:
                    poista_tuote(conn, lukija);
                    break;
                case 5:
                    nayta_paketit(conn, lukija);
                    break;
                case 6:
                    luo_paketti(conn, lukija);
                    break;
                case 7:
                    muuta_paketti(conn, lukija);
                    break;
                case 8:
                    poista_paketti(conn, lukija);
                    break;
                case 9:
                    paketin_hinta(conn, lukija);
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Virheellinen toiminnon valinta!");
                    break;
            }
        } while (running);
    }
    
    public static void ui() {
        
    // Tässä methodissa tulostetaan ohjelman päävalikko
        
        System.out.println("-------------------------------------------------");
        System.out.println("Tietokone paketin suunittelu ohjelma");
        System.out.println("");
        System.out.println("Valitse toiminto:");
        System.out.println("");
        System.out.println("1. Näytä tuotteet.");
        System.out.println("2. Lisää tietokantaan uusi tuote.");
        System.out.println("3. Muuta olemassa olevan tuotteen tietoja");
        System.out.println("4. Poista tuote tietokannasta");
        System.out.println("-------------------------------------------------");
        System.out.println("5. Näytä olemassa olevat tietokone paketit");
        System.out.println("6. Luo tietokantaan uusi tietokone paketti");
        System.out.println("7. Muuta olemassa olevaa tietokone pakettia");
        System.out.println("8. Poista tietokone paketti tietokannasta");
        System.out.println("9. Näytä valitseman paketin hinta");
        System.out.println("");
        System.out.println("0. Sulje ohjelma");
        System.out.println("-------------------------------------------------");
    }
    
    // ------------------------------------------
    // Tässä alkaa tuotteitten käsittely methodit
    // ------------------------------------------
    
    public static void nayta_tuotteet(Connection conn, Scanner lukija) {
    
    // Tässä methodissa tulostetaan haluamasi kategorian kaikki tuotteet
        
        System.out.println("Minkä kategorian haluat nähdä?");
        System.out.println("Saatavilla olevat tuote kategoriat:");
        System.out.println("1. Emolevy");
        System.out.println("2. Prosessori");
        System.out.println("3. Virtalähde");
        System.out.println("4. Muisti");
        System.out.println("5. Näytönohjain");
        System.out.println("6. Kovalevy / SSD");
        System.out.println("7. Kotelo");
        System.out.println("8. Muut (esim. äänikortit, USB-kortit yms) EI VIELÄ TOTEUTETTU");
        System.out.println("");
        System.out.println("0. Peruuta");
        
        int tuoteNayta = Integer.valueOf(lukija.nextLine());
        
        switch (tuoteNayta) {
            case 1:
                
                // Tässä näytetään kaikki emolevyt
                
                String queryMB = "SELECT * FROM emolevyt";
                try (Statement itemShowMB = conn.createStatement()) {
                    ResultSet rs = itemShowMB.executeQuery(queryMB);
                    System.out.println("ID\tValmistaja\tMalli\t\tKanta\tHinta\tVarastossa");
                    while (rs.next()) {
                        int idMB = rs.getInt("id");
                        String valmistajaMB = rs.getString("valmistaja");
                        String malliMB = rs.getString("malli");
                        String kantaMB = rs.getString("kanta");
                        int hintaMB = rs.getInt("hinta");
                        int varastossaMB = rs.getInt("varastossa");
                        System.out.println(idMB + "\t" + valmistajaMB + "\t" + malliMB + "\t" + kantaMB + "\t" + hintaMB + "\t" + varastossaMB);
                    }
                } catch (SQLException e) {
                    System.out.println("ERROR: " + e);
                }
                break;
                
            case 2:
                
                // Tässä näytetään kaikki prosessorit
                
                String queryCPU = "SELECT * FROM prosessorit";
                try (Statement itemShowCPU = conn.createStatement()) {
                    ResultSet rs = itemShowCPU.executeQuery(queryCPU);
                    System.out.println("ID\tValmistaja\tMalli\t\tKanta\tHinta\tVarastossa");
                    while (rs.next()) {
                        int idCPU = rs.getInt("id");
                        String valmistajaCPU = rs.getString("valmistaja");
                        String malliCPU = rs.getString("malli");
                        String kantaCPU = rs.getString("kanta");
                        int hintaCPU = rs.getInt("hinta");
                        int varastossaCPU = rs.getInt("varastossa");
                        System.out.println(idCPU + "\t" + valmistajaCPU + "\t" + malliCPU + "\t" + kantaCPU + "\t" + hintaCPU + "\t" + varastossaCPU);
                    }
                } catch (SQLException e) {
                    System.out.println("ERROR: " + e);
                }
                break;
                
            case 3:
                
                // Tässä näytetään kaikki virtalähteet
                
                String queryPWR = "SELECT * FROM powerit";
                try (Statement itemShowPWR = conn.createStatement()) {
                    ResultSet rs = itemShowPWR.executeQuery(queryPWR);
                    System.out.println("ID\tValmistaja\tMalli\t\tTeho\tHinta\tVarastossa");
                    while (rs.next()) {
                        int idPWR = rs.getInt("id");
                        String valmistajaPWR = rs.getString("valmistaja");
                        String malliPWR = rs.getString("malli");
                        String tehoPWR = rs.getString("teho");
                        int hintaPWR = rs.getInt("hinta");
                        int varastossaPWR = rs.getInt("varastossa");
                        System.out.println(idPWR + "\t" + valmistajaPWR + "\t" + malliPWR + "\t" + tehoPWR + "\t" + hintaPWR + "\t" + varastossaPWR);
                    }
                } catch (SQLException e) {
                    System.out.println("ERROR: " + e);
                }
                break;
            
            case 4:
                
                // Tässä näytetään kaikki muistit
                
                String queryRAM = "SELECT * FROM muisti";
                try (Statement itemShowRAM = conn.createStatement()) {
                    ResultSet rs = itemShowRAM.executeQuery(queryRAM);
                    System.out.println("ID\tValmistaja\tMalli\t\tKoko\tMäärä\tHinta\tVarastossa");
                    while (rs.next()) {
                        int idRAM = rs.getInt("id");
                        String valmistajaRAM = rs.getString("valmistaja");
                        String malliRAM = rs.getString("malli");
                        int kokoRAM = rs.getInt("koko");
                        int maaraRAM = rs.getInt("maara");
                        int hintaRAM = rs.getInt("hinta");
                        int varastossaRAM = rs.getInt("varastossa");
                        System.out.println(idRAM + "\t" + valmistajaRAM + "\t" + malliRAM + "\t" + kokoRAM + "\t" + maaraRAM + "\t" + hintaRAM + "\t" + varastossaRAM);
                    }
                } catch (SQLException e) {
                    System.out.println("ERROR: " + e);
                }
                break;
                
            case 5:
                
                // Tässä näytetään kaikki näytönohjaimet
                
                String queryGPU = "SELECT * FROM naytonohjaimet";
                try (Statement itemShowGPU = conn.createStatement()) {
                    ResultSet rs = itemShowGPU.executeQuery(queryGPU);
                    System.out.println("ID\tValmistaja\tMalli\t\tHinta\tVarastossa");
                    while (rs.next()) {
                        int idGPU = rs.getInt("id");
                        String valmistajaGPU = rs.getString("valmistaja");
                        String malliGPU = rs.getString("malli");
                        int hintaGPU = rs.getInt("hinta");
                        int varastossaGPU = rs.getInt("varastossa");
                        System.out.println(idGPU + "\t" + valmistajaGPU + "\t" + malliGPU + "\t" + hintaGPU + "\t" + varastossaGPU);
                    }
                } catch (SQLException e) {
                    System.out.println("ERROR: " + e);
                }
                break;
                
            case 6:
                
                // Tässä näytetään kaikki levyt
                
                String querySTRG = "SELECT * FROM levyt";
                try (Statement itemShowSTRG = conn.createStatement()) {
                    ResultSet rs = itemShowSTRG.executeQuery(querySTRG);
                    System.out.println("ID\tValmistaja\tMalli\t\tTyyppi\tKoko\tHinta\tVarastossa");
                    while (rs.next()) {
                        int idSTRG = rs.getInt("id");
                        String valmistajaSTRG = rs.getString("valmistaja");
                        String malliSTRG = rs.getString("malli");
                        String tyyppiSTRG = rs.getString("tyyppi");
                        int kokoSTRG = rs.getInt("koko");
                        int hintaSTRG = rs.getInt("hinta");
                        int varastossaSTRG = rs.getInt("varastossa");
                        System.out.println(idSTRG + "\t" + valmistajaSTRG + "\t" + malliSTRG + "\t" + tyyppiSTRG + "\t" + kokoSTRG + "\t" + hintaSTRG + "\t" + varastossaSTRG);
                    }
                } catch (SQLException e) {
                    System.out.println("ERROR: " + e);
                }
                break;
                
            case 7:
                
                // Tässä näytetään kaikki kotelot
                
                String queryCASE = "SELECT * FROM kotelot";
                try (Statement itemShowCASE = conn.createStatement()) {
                    ResultSet rs = itemShowCASE.executeQuery(queryCASE);
                    System.out.println("ID\tValmistaja\tMalli\t\tHinta\tVarastossa");
                    while (rs.next()) {
                        int idCASE = rs.getInt("id");
                        String valmistajaCASE = rs.getString("valmistaja");
                        String malliCASE = rs.getString("malli");
                        int hintaCASE = rs.getInt("hinta");
                        int varastossaCASE = rs.getInt("varastossa");
                        System.out.println(idCASE + "\t" + valmistajaCASE + "\t" + malliCASE + "\t" + hintaCASE + "\t" + varastossaCASE);
                    }
                } catch (SQLException e) {
                    System.out.println("ERROR: " + e);
                }
                break;
                
            case 8:
                
                // Tässä näytetään kaikki muut komponentit [TOIMINTOA EI VIELÄ LISÄTTY]
                
                System.out.println("Toimintoa ei vielä lisätty, harkinnassa onko lisäosissa mitään järkeä :D");
                break;
                
            case 0:
                
                // Tässä peruutetaan takasin päävalikkoon
                
                break;
                
            default:
                System.out.println("ERROR: Virheellinen valinta!");
        }
    }
    
    public static void lisaa_tuote(Connection conn, Scanner lukija) {
        
    // Tässä methodissa kysytään minkä kategorian tuote halutaan lisätä tietokantaan
        
        System.out.println("Minkä tuotteen haluat lisätä?");
        System.out.println("Saatavilla olevat tuote kategoriat:");
        System.out.println("1. Emolevy");
        System.out.println("2. Prosessori");
        System.out.println("3. Virtalähde");
        System.out.println("4. Muisti");
        System.out.println("5. Näytönohjain");
        System.out.println("6. Kovalevy / SSD");
        System.out.println("7. Kotelo");
        System.out.println("8. Muut (esim. äänikortit, USB-kortit yms) EI VIELÄ TOTEUTETTU");
        System.out.println("");
        System.out.println("0. Peruuta");
        
        int tuoteValinta = Integer.valueOf(lukija.nextLine());
        Boolean addProductState = true;
        
        do {
            switch (tuoteValinta) {
                case 1:
                
                // Tässä kysytään emolevyn tiedot.
                    
                    System.out.println("Syötä emolevyn valmistaja:");
                    String valmistajaMB = lukija.nextLine();
                    System.out.println("Syötä emolevyn malli:");
                    String malliMB = lukija.nextLine();
                    System.out.println("Syötä emolevyn prosessorin kanta malli:");
                    String kantaMB = lukija.nextLine();
                    System.out.println("Syötä emolevyn hinta:");
                    int hintaMB = Integer.valueOf(lukija.nextLine());
                    System.out.println("Syötä kuinka monta emolevyä on varastossa:");
                    int varastossaMB = Integer.valueOf(lukija.nextLine());
                    
                // ja tässä lisätään ne tietokantaan.    
                    
                    String queryMB = "INSERT INTO emolevyt (valmistaja, malli, kanta, hinta, varastossa)"
                            + "VALUES ('"+valmistajaMB+"','"+malliMB+"','"+kantaMB+"','"+hintaMB+"','"+varastossaMB+"');";
                    try (Statement mbAdd = conn.createStatement()) {
                        int rs = mbAdd.executeUpdate(queryMB);
                        System.out.println("Emolevyn lisäys tietokantaan onnistui!");
                    } catch (SQLException e) {
                        System.out.println("ERROR: " + e);
                    }
                    addProductState = false;
                    break;
                    
                case 2:
                
                // Tässä kysytään prosessorin tiedot.
                    
                    System.out.println("Syötä prosessorin valmistaja:");
                    String valmistajaCPU = lukija.nextLine();
                    System.out.println("Syötä prosessorin malli:");
                    String malliCPU = lukija.nextLine();
                    System.out.println("Syötä prosessorin kanta malli:");
                    String kantaCPU = lukija.nextLine();
                    System.out.println("Syötä prosessorin hinta:");
                    int hintaCPU = Integer.valueOf(lukija.nextLine());
                    System.out.println("Syötä kuinka monta prosessoria on varastossa:");
                    int varastossaCPU = Integer.valueOf(lukija.nextLine());
                    
                // ja tässä lisätään ne tietokantaan.    
                    
                    String queryCPU = "INSERT INTO prosessorit (valmistaja, malli, kanta, hinta, varastossa)"
                            + "VALUES ('"+valmistajaCPU+"','"+malliCPU+"','"+kantaCPU+"','"+hintaCPU+"','"+varastossaCPU+"');";
                    try (Statement cpuAdd = conn.createStatement()) {
                        int rs = cpuAdd.executeUpdate(queryCPU);
                        System.out.println("Prosessorin lisäys tietokantaan onnistui!");
                    } catch (SQLException e) {
                        System.out.println("ERROR: " + e);
                    }
                    addProductState = false;
                    break;
                    
                case 3:
                
                // Tässä kysytään virtalähteen tiedot.
                    
                    System.out.println("Syötä virtalähteen valmistaja:");
                    String valmistajaPWR = lukija.nextLine();
                    System.out.println("Syötä virtalähteen malli:");
                    String malliPWR = lukija.nextLine();
                    System.out.println("Syötä virtalähteen teho (Syötä Watteina esim. 750):");
                    String tehoPWR = lukija.nextLine();
                    System.out.println("Syötä virtalähteen hinta:");
                    int hintaPWR = Integer.valueOf(lukija.nextLine());
                    System.out.println("Syötä kuinka monta virtalähdettä on varastossa:");
                    int varastossaPWR = Integer.valueOf(lukija.nextLine());
                    
                // ja tässä lisätään ne tietokantaan.   
                    
                    String queryPWR = "INSERT INTO powerit (valmistaja, malli, teho, hinta, varastossa)"
                            + "VALUES ('"+valmistajaPWR+"','"+malliPWR+"','"+tehoPWR+"','"+hintaPWR+"','"+varastossaPWR+"');";
                    try (Statement pwrAdd = conn.createStatement()) {
                        int rs = pwrAdd.executeUpdate(queryPWR);
                        System.out.println("Virtalähteen lisäys tietokantaan onnistui!");
                    } catch (SQLException e) {
                        System.out.println("ERROR: " + e);
                    }
                    addProductState = false;
                    break;
                    
                case 4:
                    
                // Tässä kysytään muistin tiedot.
                    
                    System.out.println("Syötä muistin valmistaja:");
                    String valmistajaRAM = lukija.nextLine();
                    System.out.println("Syötä muistin malli:");
                    String malliRAM = lukija.nextLine();
                    System.out.println("Syötä muistin koko (JOS PAKETISSA ON MONTA KAMPAA, LAITA VAIN YHDEN KAMMAN KOKO!)");
                    String kokoRAM = lukija.nextLine();
                    System.out.println("Syötä muistikampojen määrä:");
                    int maaraRAM = Integer.valueOf(lukija.nextLine());
                    System.out.println("Syötä muistin hinta:");
                    int hintaRAM = Integer.valueOf(lukija.nextLine());
                    System.out.println("Syötä kuinka monta muistikampaa on varastossa:");
                    int varastossaRAM = Integer.valueOf(lukija.nextLine());
                
                // ja tässä lisätään ne tietokantaan.
                    
                    String queryRAM = "INSERT INTO muisti (valmistaja, malli, koko, maara, hinta, varastossa)"
                            + "VALUES ('"+valmistajaRAM+"','"+malliRAM+"','"+kokoRAM+"','"+maaraRAM+"','"+hintaRAM+"','"+varastossaRAM+"');";
                    try (Statement ramAdd = conn.createStatement()) {
                        int rs = ramAdd.executeUpdate(queryRAM);
                        System.out.println("Muistin lisäys tietokantaan onnistui!");
                    } catch (SQLException e) {
                        System.out.println("ERROR: " + e);
                    }
                    addProductState = false;
                    break;
                    
                case 5:
                    
                // Tässä kysytään näytönohjaimen tiedot.
                    
                    System.out.println("Syötä näytönohjaimen valmistaja:");
                    String valmistajaGPU = lukija.nextLine();
                    System.out.println("Syötä näytönohjaimen malli:");
                    String malliGPU = lukija.nextLine();
                    System.out.println("Syötä näytönohjaimen hinta:");
                    int hintaGPU = Integer.valueOf(lukija.nextLine());
                    System.out.println("Syötä kuinka monta näytönohjainta on varastossa:");
                    int varastossaGPU = Integer.valueOf(lukija.nextLine());
                    
                // ja tässä lisätään ne tietokantaan.    
                    
                    String queryGPU = "INSERT INTO naytonohjaimet (valmistaja, malli, hinta, varastossa)"
                            + "VALUES ('"+valmistajaGPU+"','"+malliGPU+"','"+hintaGPU+"','"+varastossaGPU+"');";
                    try (Statement gpuAdd = conn.createStatement()) {
                        int rs = gpuAdd.executeUpdate(queryGPU);
                        System.out.println("Näytonohjaimen lisäys tietokantaan onnistui!");
                    } catch (SQLException e) {
                        System.out.println("ERROR: " + e);
                    }
                    addProductState = false;
                    break;
                    
                case 6:
                    
                // Tässä kysytään lisätäänkö kovalevy vai ssd
                    
                    System.out.println("Kumpi lisätään?");
                    System.out.println("1. Kovalevy");
                    System.out.println("2. SSD");
                    
                    int choiceSTRG = Integer.valueOf(lukija.nextLine());
                    
                    if (choiceSTRG == 1) {
                        
                //  Tässä kysytään HDD:n tiedot         
                        
                            System.out.println("Syötä kovalevyn valmistaja:");
                            String valmistajaHDD = lukija.nextLine();
                            System.out.println("Syötä kovalevyn malli:");
                            String malliHDD = lukija.nextLine();
                            String tyyppiHDD = "HDD";
                            System.out.println("Syötä kovalevyn koko GB:nä");
                            int kokoHDD = Integer.valueOf(lukija.nextLine());
                            System.out.println("Syötä kovalevyn hinta:");
                            int hintaHDD = Integer.valueOf(lukija.nextLine());
                            System.out.println("Syötä kuinka monta kovalevyä on varastossa:");
                            int varastossaHDD = Integer.valueOf(lukija.nextLine());
                            
                // ja tässä lisätään ne tietokantaan.  
                
                            String queryHDD = "INSERT INTO levyt (valmistaja, malli, tyyppi, koko, hinta, varastossa)"
                                    + "VALUES ('"+valmistajaHDD+"','"+malliHDD+"','"+tyyppiHDD+"','"+kokoHDD+"','"+hintaHDD+"','"+varastossaHDD+"');";
                            try (Statement hddAdd = conn.createStatement()) {
                                int rs = hddAdd.executeUpdate(queryHDD);
                                System.out.println("Kovalevyn lisäys tietokantaan onnistui!");
                            } catch (SQLException e) {
                                System.out.println("ERROR: " + e);
                            }
                            
                    } else if (choiceSTRG == 2) {

                //  Tässä kysytään SSD:n tiedot         
                        
                            System.out.println("Syötä SSD:n valmistaja:");
                            String valmistajaSSD = lukija.nextLine();
                            System.out.println("Syötä SSD:n malli:");
                            String malliSSD = lukija.nextLine();
                            String tyyppiSSD = "SSD";
                            System.out.println("Syötä SSD:n koko GB:nä");
                            int kokoSSD = Integer.valueOf(lukija.nextLine());
                            System.out.println("Syötä SSD:n hinta:");
                            int hintaSSD = Integer.valueOf(lukija.nextLine());
                            System.out.println("Syötä kuinka monta SSD:tä on varastossa:");
                            int varastossaSSD = Integer.valueOf(lukija.nextLine());
                            
                // ja tässä lisätään ne tietokantaan.   
                
                            String querySSD = "INSERT INTO levyt (valmistaja, malli, tyyppi, koko, hinta, varastossa)"
                                    + "VALUES ('"+valmistajaSSD+"','"+malliSSD+"','"+tyyppiSSD+"','"+kokoSSD+"','"+hintaSSD+"','"+varastossaSSD+"');";
                            try (Statement ssdAdd = conn.createStatement()) {
                                int rs = ssdAdd.executeUpdate(querySSD);
                                System.out.println("SSD:n lisäys tietokantaan onnistui!");
                            } catch (SQLException e) {
                                System.out.println("ERROR: " + e);
                            }
                            
                    } else {
                        System.out.println("ERROR: Virheellinen valinta!");
                    }    
                            
                    addProductState = false;
                    break;
                    
                case 7:
                    
                // Tässä kysytään kotelon tiedot.
                    
                    System.out.println("Syötä kotelonn valmistaja:");
                    String valmistajaCASE = lukija.nextLine();
                    System.out.println("Syötä kotelon malli:");
                    String malliCASE = lukija.nextLine();
                    System.out.println("Syötä kotelonn hinta:");
                    int hintaCASE = Integer.valueOf(lukija.nextLine());
                    System.out.println("Syötä kuinka monta koteloa on varastossa:");
                    int varastossaCASE = Integer.valueOf(lukija.nextLine());
                            
                // ja tässä lisätään ne tietokantaan.  
                    
                    String queryCASE = "INSERT INTO kotelot (valmistaja, malli, hinta, varastossa)"
                            + "VALUES ('"+valmistajaCASE+"','"+malliCASE+"','"+hintaCASE+"','"+varastossaCASE+"');";
                    try (Statement caseAdd = conn.createStatement()) {
                        int rs = caseAdd.executeUpdate(queryCASE);
                        System.out.println("Kotelon lisäys tietokantaan onnistui!");
                    } catch (SQLException e) {
                        System.out.println("ERROR: " + e);
                    }
                    addProductState = false;
                    break;
                    
                case 8:
                    
                // Tähän tulee muiden komponenttien lisääminen, jos siitä on tarvetta.
                    
                    addProductState = false;
                    break;
                    
                case 0:
                    
                // Tässä peruutetaan takaisin päävalikkoon
                    
                    addProductState = false;
                    break;
                    
                default:
                    System.out.println("ERROR: Virheellinen valinta!");
                    break;
            }
        } while (addProductState);
    }
    
    public static void muuta_tuote(Connection conn, Scanner lukija) {
        
    //Tässä kysytään minkä kategorian tuotteen käyttäjä haluaa poistaa
        
        System.out.println("Minkä tuotteen tietoja haluat muuttaa?");
        System.out.println("Saatavilla olevat tuote kategoriat:");
        System.out.println("1. Emolevy");
        System.out.println("2. Prosessori");
        System.out.println("3. Virtalähde");
        System.out.println("4. Muisti");
        System.out.println("5. Näytönohjain");
        System.out.println("6. Kovalevy / SSD");
        System.out.println("7. Kotelo");
        System.out.println("8. Muut (esim. äänikortit, USB-kortit yms) EI VIELÄ TOTEUTETTU");
        System.out.println("");
        System.out.println("0. Peruuta");
        
        int kategoriaValinta = Integer.valueOf(lukija.nextLine());
        Boolean editProductState = true;
        
        do {
            switch (kategoriaValinta) {
                case 1:
                    
                // Kysytään emolevyn ID
                    
                    System.out.println("Valitse tuotteen ID, jota haluat muokata.");
                    int tuoteValintaMB = Integer.valueOf(lukija.nextLine());
                    
                    // Kysytään emolevyn uudet tiedot
                    
                    System.out.println("Syötä emolevyn valmistaja:");
                    String valmistajaMB = lukija.nextLine();
                    System.out.println("Syötä emolevyn malli:");
                    String malliMB = lukija.nextLine();
                    System.out.println("Syötä emolevyn prosessorin kanta malli:");
                    String kantaMB = lukija.nextLine();
                    System.out.println("Syötä emolevyn hinta:");
                    int hintaMB = Integer.valueOf(lukija.nextLine());
                    System.out.println("Syötä kuinka monta emolevyä on varastossa:");
                    int varastossaMB = Integer.valueOf(lukija.nextLine());
                    
                    String queryMB = "UPDATE emolevyt SET valmistaja = '"+valmistajaMB+"', malli = '"+malliMB+"', kanta = '"+kantaMB+"', hinta = '"+hintaMB+"', varastossa = '"+varastossaMB+"' WHERE id = '"+tuoteValintaMB+"'";
                    
                    // Lisätään uudet tiedot tietokantaan
                    
                    try (Statement mbAdd = conn.createStatement()) {
                        int rs = mbAdd.executeUpdate(queryMB);
                        System.out.println("Emolevyn tietojen päivitys tietokantaan onnistui!");
                    } catch (SQLException e) {
                        System.out.println("ERROR: " + e);
                    }
                    editProductState = false;
                    break;
                    
                case 2:
                    
                // Kysytään prosessorin ID
                    
                    System.out.println("Valitse tuotteen ID, jota haluat muokata.");
                    int tuoteValintaCPU = Integer.valueOf(lukija.nextLine());
                    
                    // Kysytään prosessorin uudet tiedot
                    
                    System.out.println("Syötä prosessorin valmistaja:");
                    String valmistajaCPU = lukija.nextLine();
                    System.out.println("Syötä prosessorin malli:");
                    String malliCPU = lukija.nextLine();
                    System.out.println("Syötä prosessorin kanta malli:");
                    String kantaCPU = lukija.nextLine();
                    System.out.println("Syötä prosessorin hinta:");
                    int hintaCPU = Integer.valueOf(lukija.nextLine());
                    System.out.println("Syötä kuinka monta prosessoria on varastossa:");
                    int varastossaCPU = Integer.valueOf(lukija.nextLine());
                    
                    String queryCPU = "UPDATE prosessorit SET valmistaja = '"+valmistajaCPU+"', malli = '"+malliCPU+"', kanta = '"+kantaCPU+"', hinta = '"+hintaCPU+"', varastossa = '"+varastossaCPU+"' WHERE id = '"+tuoteValintaCPU+"'";
                    
                    // Lisätään uudet tiedot tietokantaan
                    
                    try (Statement cpuAdd = conn.createStatement()) {
                        int rs = cpuAdd.executeUpdate(queryCPU);
                        System.out.println("Prosessorin tietojen päivitys tietokantaan onnistui!");
                    } catch (SQLException e) {
                        System.out.println("ERROR: " + e);
                    }
                    editProductState = false;
                    break;
                    
                case 3:
                
                // Kysytään virtalähteen ID
                    
                    System.out.println("Valitse tuotteen ID, jota haluat muokata.");
                    int tuoteValintaPWR = Integer.valueOf(lukija.nextLine());
                    
                    // Kysytään virtalähteen uudet tiedot
                    
                    System.out.println("Syötä virtalähteen valmistaja:");
                    String valmistajaPWR = lukija.nextLine();
                    System.out.println("Syötä virtalähteen malli:");
                    String malliPWR = lukija.nextLine();
                    System.out.println("Syötä virtalähteen teho (Syötä Watteina esim. 750):");
                    String tehoPWR = lukija.nextLine();
                    System.out.println("Syötä virtalähteen hinta:");
                    int hintaPWR = Integer.valueOf(lukija.nextLine());
                    System.out.println("Syötä kuinka monta virtalähdettä on varastossa:");
                    int varastossaPWR = Integer.valueOf(lukija.nextLine());
                    
                    String queryPWR = "UPDATE powerit SET valmistaja = '"+valmistajaPWR+"', malli = '"+malliPWR+"', teho = '"+tehoPWR+"', hinta = '"+hintaPWR+"', varastossa = '"+varastossaPWR+"' WHERE id = '"+tuoteValintaPWR+"'";
                    
                    // Lisätään uudet tiedot tietokantaan
                    
                    try (Statement pwrAdd = conn.createStatement()) {
                        int rs = pwrAdd.executeUpdate(queryPWR);
                        System.out.println("Virtalähteen tietojen päivitys tietokantaan onnistui!");
                    } catch (SQLException e) {
                        System.out.println("ERROR: " + e);
                    }
                    editProductState = false;
                    break;
                    
                case 4:
                
                // Kysytään muistin ID
                    
                    System.out.println("Valitse tuotteen ID, jota haluat muokata.");
                    int tuoteValintaRAM = Integer.valueOf(lukija.nextLine());
                    
                    // Kysytään muistin uudet tiedot
                    
                    System.out.println("Syötä muistin valmistaja:");
                    String valmistajaRAM = lukija.nextLine();
                    System.out.println("Syötä muistin malli:");
                    String malliRAM = lukija.nextLine();
                    System.out.println("Syötä muistin koko (JOS PAKETISSA ON MONTA KAMPAA, LAITA VAIN YHDEN KAMMAN KOKO!)");
                    String kokoRAM = lukija.nextLine();
                    System.out.println("Syötä muistikampojen määrä:");
                    int maaraRAM = Integer.valueOf(lukija.nextLine());
                    System.out.println("Syötä muistin hinta:");
                    int hintaRAM = Integer.valueOf(lukija.nextLine());
                    System.out.println("Syötä kuinka monta muistikampaa on varastossa:");
                    int varastossaRAM = Integer.valueOf(lukija.nextLine());
                    
                    // Lisätään uudet tiedot tietokantaan
                    
                    String queryRAM = "UPDATE muisti SET valmistaja = '"+valmistajaRAM+"', malli = '"+malliRAM+"', koko = '"+kokoRAM+"', maara = '"+maaraRAM+"', hinta = '"+hintaRAM+"', varastossa = '"+varastossaRAM+"' WHERE id = '"+tuoteValintaRAM+"'";
                    try (Statement ramAdd = conn.createStatement()) {
                        int rs = ramAdd.executeUpdate(queryRAM);
                        System.out.println("Muistin tietojen päivitys tietokantaan onnistui!");
                    } catch (SQLException e) {
                        System.out.println("ERROR: " + e);
                    }
                    editProductState = false;
                    break;
                case 5:
                    
                // Kysytään näytönohjaimen ID
                    
                    System.out.println("Valitse tuotteen ID, jota haluat muokata.");
                    int tuoteValintaGPU = Integer.valueOf(lukija.nextLine());
                    
                    // Kysytään näytönohjaimen uudet tiedot
                    
                    System.out.println("Syötä näytönohjaimen valmistaja:");
                    String valmistajaGPU = lukija.nextLine();
                    System.out.println("Syötä näytönohjaimen malli:");
                    String malliGPU = lukija.nextLine();
                    System.out.println("Syötä näytönohjaimen hinta:");
                    int hintaGPU = Integer.valueOf(lukija.nextLine());
                    System.out.println("Syötä kuinka monta näytönohjainta on varastossa:");
                    int varastossaGPU = Integer.valueOf(lukija.nextLine());
                    
                    // Lisätään uudet tiedot tietokantaan
                    
                    String queryGPU = "UPDATE naytonohjaimet SET valmistaja = '"+valmistajaGPU+"', malli = '"+malliGPU+"', hinta = '"+hintaGPU+"', varastossa = '"+varastossaGPU+"' WHERE id = '"+tuoteValintaGPU+"'";
                    try (Statement gpuAdd = conn.createStatement()) {
                        int rs = gpuAdd.executeUpdate(queryGPU);
                        System.out.println("Näytönohjaimen tietojen päivitys tietokantaan onnistui!");
                    } catch (SQLException e) {
                        System.out.println("ERROR: " + e);
                    }
                    editProductState = false;
                    break;
                case 6:
                        
                // Kysytään levyn ID
                    
                    System.out.println("Valitse tuotteen ID, jota haluat muokata.");
                    int tuoteValintaSTRG = Integer.valueOf(lukija.nextLine());
                    
                    // Kysytään levyn uudet tiedot
                    
                    System.out.println("Syötä levyn valmistaja:");
                    String valmistajaSTRG = lukija.nextLine();
                    System.out.println("Syötä levyn malli:");
                    String malliSTRG = lukija.nextLine();
                    System.out.println("Syötä levyn koko:");
                    String kokoSTRG = lukija.nextLine();
                    System.out.println("Syötä levyn hinta:");
                    int hintaSTRG = Integer.valueOf(lukija.nextLine());
                    System.out.println("Syötä kuinka monta levyä on varastossa:");
                    int varastossaSTRG = Integer.valueOf(lukija.nextLine());
                    
                    // Lisätään uudet tiedot tietokantaan
                    
                    String querySTRG = "UPDATE levyt SET valmistaja = '"+valmistajaSTRG+"', malli = '"+malliSTRG+"', koko = '"+kokoSTRG+"', hinta = '"+hintaSTRG+"', varastossa = '"+varastossaSTRG+"' WHERE id = '"+tuoteValintaSTRG+"'";
                    try (Statement strgAdd = conn.createStatement()) {
                        int rs = strgAdd.executeUpdate(querySTRG);
                        System.out.println("Levyn tietojen päivitys tietokantaan onnistui!");
                    } catch (SQLException e) {
                        System.out.println("ERROR: " + e);
                    }
                    editProductState = false;
                    break;
                case 7:
                        
                // Kysytään kotelon ID
                    
                    System.out.println("Valitse tuotteen ID, jota haluat muokata.");
                    int tuoteValintaCASE = Integer.valueOf(lukija.nextLine());
                    
                    // Kysytään kotelon uudet tiedot
                    
                    System.out.println("Syötä kotelon valmistaja:");
                    String valmistajaCASE = lukija.nextLine();
                    System.out.println("Syötä kotelon malli:");
                    String malliCASE = lukija.nextLine();
                    System.out.println("Syötä kotelon hinta:");
                    int hintaCASE = Integer.valueOf(lukija.nextLine());
                    System.out.println("Syötä kuinka monta koteloa on varastossa:");
                    int varastossaCASE = Integer.valueOf(lukija.nextLine());
                    
                    // Lisätään uudet tiedot tietokantaan
                    
                    String queryCASE = "UPDATE kotelot SET valmistaja = '"+valmistajaCASE+"', malli = '"+malliCASE+"', hinta = '"+hintaCASE+"', varastossa = '"+varastossaCASE+"' WHERE id = '"+tuoteValintaCASE+"'";
                    try (Statement caseAdd = conn.createStatement()) {
                        int rs = caseAdd.executeUpdate(queryCASE);
                        System.out.println("Kotelon tietojen päivitys tietokantaan onnistui!");
                    } catch (SQLException e) {
                        System.out.println("ERROR: " + e);
                    }
                    editProductState = false;
                    break;
                case 8:
                    
                // Tähän tulee muiden komponenttien tietojen päivitys, jos sille on tarvetta.
                    
                    editProductState = false;
                    break;
                case 0:
                // Tässä peruutetaan takaisin päävalikkoon
                    editProductState = false;
                    break;
                default:
                    System.out.println("ERROR: Virheellinen valinta!");
                    break;
            }
        } while (editProductState);
    }

    public static void poista_tuote(Connection conn, Scanner lukija) {
        
        // Kysytään minkä kategorian tuotteen käyttäjä haluaa poistaa
        
        System.out.println("Minkä tuotteen tietoja haluat poistaa?");
        System.out.println("Saatavilla olevat tuote kategoriat:");
        System.out.println("1. Emolevy");
        System.out.println("2. Prosessori");
        System.out.println("3. Virtalähde");
        System.out.println("4. Muisti");
        System.out.println("5. Näytönohjain");
        System.out.println("6. Kovalevy / SSD");
        System.out.println("7. Kotelo");
        System.out.println("8. Muut (esim. äänikortit, USB-kortit yms) EI VIELÄ TOTEUTETTU");
        System.out.println("");
        System.out.println("0. Peruuta");
        
        int kategoriaValinta = Integer.valueOf(lukija.nextLine());
        Boolean delProductState = true;
        
        do {
            switch (kategoriaValinta) {
                case 1:
                    
                // Valitaan emolevyn ID
                    
                    System.out.println("Valitse tuotteen ID, jonka haluat poistaa.");
                    int tuoteValintaMB = Integer.valueOf(lukija.nextLine());
                    
                    String queryMB = "DELETE FROM emolevyt WHERE id = '"+tuoteValintaMB+"'";
                    
                    // Varmistetaan haluaako käyttäjä poistaa emolevyn
                    
                    System.out.println("Oletko varma että haluat poistaa Emolevyn, jonka ID on: " + tuoteValintaMB + "?");
                    System.out.println("1. Kyllä, poista tuote");
                    System.out.println("2. En, peruuttaa takaisin päävalikkoon");
                    int poistoValintaMB = Integer.valueOf(lukija.nextLine());
                    if (poistoValintaMB == 1) {
                        try (Statement mbAdd = conn.createStatement()) {
                        int rs = mbAdd.executeUpdate(queryMB);
                        System.out.println("Emolevyn poisto onnistui!");
                    } catch (SQLException e) {
                        System.out.println("ERROR: " + e);
                    }
                    } else {
                        System.out.println("Peruutetaan...");
                    }
                    delProductState = false;
                    break;
                    
                case 2:
                    
                // Valitaan prosessorin ID
                    
                    System.out.println("Valitse tuotteen ID, jonka haluat poistaa.");
                    int tuoteValintaCPU = Integer.valueOf(lukija.nextLine());
                    
                    String queryCPU = "DELETE FROM prosessorit WHERE id = '"+tuoteValintaCPU+"'";
                    
                    // Varmistetaan haluaako käyttäjä poistaa prosessorin
                    
                    System.out.println("Oletko varma että haluat poistaa Prosessorin, jonka ID on: " + tuoteValintaCPU + "?");
                    System.out.println("1. Kyllä, poista tuote");
                    System.out.println("2. En, peruuttaa takaisin päävalikkoon");
                    int poistoValintaCPU = Integer.valueOf(lukija.nextLine());
                    if (poistoValintaCPU == 1) {
                        try (Statement cpuAdd = conn.createStatement()) {
                        int rs = cpuAdd.executeUpdate(queryCPU);
                        System.out.println("Prosessorin poisto onnistui!");
                    } catch (SQLException e) {
                        System.out.println("ERROR: " + e);
                    }
                    } else {
                        System.out.println("Peruutetaan...");
                    }
                    delProductState = false;
                    break;
                    
                case 3:
                
                // Valitaan virtalähteen ID
                    
                    System.out.println("Valitse tuotteen ID, jonka haluat poistaa.");
                    int tuoteValintaPWR = Integer.valueOf(lukija.nextLine());
                    
                    String queryPWR = "DELETE FROM powerit WHERE id = '"+tuoteValintaPWR+"'";
                    
                    // Varmistetaan haluaako käyttäjä poistaa virtalähteen
                    
                    System.out.println("Oletko varma että haluat poistaa Virtalähteen, jonka ID on: " + tuoteValintaPWR + "?");
                    System.out.println("1. Kyllä, poista tuote");
                    System.out.println("2. En, peruuttaa takaisin päävalikkoon");
                    int poistoValintaPWR = Integer.valueOf(lukija.nextLine());
                    if (poistoValintaPWR == 1) {
                        try (Statement pwrAdd = conn.createStatement()) {
                        int rs = pwrAdd.executeUpdate(queryPWR);
                        System.out.println("Virtalähteen poisto onnistui!");
                    } catch (SQLException e) {
                        System.out.println("ERROR: " + e);
                    }
                    } else {
                        System.out.println("Peruutetaan...");
                    }
                    delProductState = false;
                    break;
                    
                case 4:
                
                // Valitaan muistin ID
                    
                    System.out.println("Valitse tuotteen ID, jonka haluat poistaa.");
                    int tuoteValintaRAM = Integer.valueOf(lukija.nextLine());
                    
                    String queryRAM = "DELETE FROM muisti WHERE id = '"+tuoteValintaRAM+"'";
                    
                    // Varmistetaan haluaako käyttäjä poistaa Muistin
                    
                    System.out.println("Oletko varma että haluat poistaa Muistin, jonka ID on: " + tuoteValintaRAM + "?");
                    System.out.println("1. Kyllä, poista tuote");
                    System.out.println("2. En, peruuttaa takaisin päävalikkoon");
                    int poistoValintaRAM = Integer.valueOf(lukija.nextLine());
                    if (poistoValintaRAM == 1) {
                        try (Statement pwrAdd = conn.createStatement()) {
                        int rs = pwrAdd.executeUpdate(queryRAM);
                        System.out.println("Muistin poisto onnistui!");
                    } catch (SQLException e) {
                        System.out.println("ERROR: " + e);
                    }
                    } else {
                        System.out.println("Peruutetaan...");
                    }
                    delProductState = false;
                    break;
                case 5:
                    
                // Valitaan näytönohjaimen ID
                    
                    System.out.println("Valitse tuotteen ID, jonka haluat poistaa.");
                    int tuoteValintaGPU = Integer.valueOf(lukija.nextLine());
                    
                    String queryGPU = "DELETE FROM naytonohjaimet WHERE id = '"+tuoteValintaGPU+"'";
                    
                    // Varmistetaan haluaako käyttäjä poistaa näytönohjaimen
                    
                    System.out.println("Oletko varma että haluat poistaa Näytönohjaimen, jonka ID on: " + tuoteValintaGPU + "?");
                    System.out.println("1. Kyllä, poista tuote");
                    System.out.println("2. En, peruuttaa takaisin päävalikkoon");
                    int poistoValintaGPU = Integer.valueOf(lukija.nextLine());
                    if (poistoValintaGPU == 1) {
                        try (Statement gpuAdd = conn.createStatement()) {
                        int rs = gpuAdd.executeUpdate(queryGPU);
                        System.out.println("Näytönohjaimen poisto onnistui!");
                    } catch (SQLException e) {
                        System.out.println("ERROR: " + e);
                    }
                    } else {
                        System.out.println("Peruutetaan...");
                    }
                    delProductState = false;
                    break;
                case 6:
                        
                // Valitaan levyn ID
                    
                    System.out.println("Valitse tuotteen ID, jonka haluat poistaa.");
                    int tuoteValintaSTRG = Integer.valueOf(lukija.nextLine());
                    
                    String querySTRG = "DELETE FROM levyt WHERE id = '"+tuoteValintaSTRG+"'";
                    
                    // Varmistetaan haluaako käyttäjä poistaa levyn
                    
                    System.out.println("Oletko varma että haluat poistaa Levyn, jonka ID on: " + tuoteValintaSTRG + "?");
                    System.out.println("1. Kyllä, poista tuote");
                    System.out.println("2. En, peruuttaa takaisin päävalikkoon");
                    int poistoValintaSTRG = Integer.valueOf(lukija.nextLine());
                    if (poistoValintaSTRG == 1) {
                        try (Statement strgAdd = conn.createStatement()) {
                        int rs = strgAdd.executeUpdate(querySTRG);
                        System.out.println("Levyn poisto onnistui!");
                    } catch (SQLException e) {
                        System.out.println("ERROR: " + e);
                    }
                    } else {
                        System.out.println("Peruutetaan...");
                    }
                    delProductState = false;
                    break;
                case 7:
                        
                // Valitaan kotelon ID
                    
                    System.out.println("Valitse tuotteen ID, jonka haluat poistaa.");
                    int tuoteValintaCASE = Integer.valueOf(lukija.nextLine());
                    
                    String queryCASE = "DELETE FROM kotelot WHERE id = '"+tuoteValintaCASE+"'";
                    
                    // Varmistetaan haluaako käyttäjä poistaa kotelon
                    
                    System.out.println("Oletko varma että haluat poistaa Kotelon, jonka ID on: " + tuoteValintaCASE + "?");
                    System.out.println("1. Kyllä, poista tuote");
                    System.out.println("2. En, peruuttaa takaisin päävalikkoon");
                    int poistoValintaCASE = Integer.valueOf(lukija.nextLine());
                    if (poistoValintaCASE == 1) {
                        try (Statement caseAdd = conn.createStatement()) {
                        int rs = caseAdd.executeUpdate(queryCASE);
                        System.out.println("Kotelon poisto onnistui!");
                    } catch (SQLException e) {
                        System.out.println("ERROR: " + e);
                    }
                    } else {
                        System.out.println("Peruutetaan...");
                    }
                    delProductState = false;
                    break;
                case 8:
                    delProductState = false;
                    break;
                case 0:
                // Tällä peruutetaan takaisin päävalikkoon
                    delProductState = false;
                    break;
                default:
                    System.out.println("ERROR: Virheellinen valinta!");
                    break;
            }
        } while (delProductState);
    }
    
    //-----------------------------------------
    // Tässä alkaa pakettien käsittely methodit
    // ----------------------------------------
    
    public static void nayta_paketit(Connection conn, Scanner lukija) {
        
        System.out.println("Valitse tietokone paketin ID...");
        int pktValinta = Integer.valueOf(lukija.nextLine());
        
        // Valmistellaan query jolla näytetään paketissa olevat tuotteet
        
        String showQuery = "SELECT mb.malli, cpu.malli, pwr.malli, ram.malli, gpu.malli, strg.malli, cse.malli "
                + "FROM paketit AS pkt "
                + "LEFT JOIN emolevyt AS mb ON pkt.mb_id = mb.id "
                + "LEFT JOIN prosessorit AS cpu ON pkt.cpu_id = cpu.id "
                + "LEFT JOIN powerit AS pwr ON pkt.pwr_id = pwr.id "
                + "LEFT JOIN muisti AS ram ON pkt.ram_id = ram.id "
                + "LEFT JOIN naytonohjaimet AS gpu ON pkt.gpu_id = gpu.id "
                + "LEFT JOIN levyt AS strg ON pkt.strg_id = strg.id "
                + "LEFT JOIN kotelot AS cse ON pkt.case_id = cse.id "
                + "AND pkt.id = '" + pktValinta + "'";
        
        // ja tässä tulostetaan tuotteet
        
        try (Statement pktShow = conn.createStatement()) {
            ResultSet rs = pktShow.executeQuery(showQuery);
            System.out.println("Emolevy  \tProsessori\tVirtalähde\tMuisti   \tLevy\tNäytönohjain\tKotelo");
            while (rs.next()) {
                        String malliMB = rs.getString("mb.malli");
                        String malliCPU = rs.getString("cpu.malli");
                        String malliPWR = rs.getString("pwr.malli");
                        String malliRAM = rs.getString("ram.malli");
                        String malliSTRG = rs.getString("strg.malli");
                        String malliGPU = rs.getString("gpu.malli");
                        String malliCASE = rs.getString("cse.malli");
                        System.out.println(malliMB + "\t" + malliCPU + "\t" + malliPWR + "\t" + malliRAM + "\t" + malliSTRG + "\t" + malliGPU + "\t" + malliCASE);
                    }
        } catch (SQLException e) {
            System.out.println("ERROR: " + e);
        }
    }
    
    public static void luo_paketti(Connection conn, Scanner lukija) {
        
        System.out.println("HUOM: Komponentit pitää valita tietokannasta tuotteen ID:llä!");
        
        // Tässä kysytään mitkä komponentit lisätään pakettiin
        
        System.out.println("Valitse pakettiin Emolevy: ");
        int idMB = Integer.valueOf(lukija.nextLine());
        System.out.println("Valitse pakettiin Prosessori: ");
        int idCPU = Integer.valueOf(lukija.nextLine());
        System.out.println("Valitse pakettiin Virtalähde: ");
        int idPWR = Integer.valueOf(lukija.nextLine());
        System.out.println("Valitse pakettiin Muisti: ");
        int idRAM = Integer.valueOf(lukija.nextLine());
        System.out.println("Valitse pakettiin Näytönohjain: ");
        int idGPU = Integer.valueOf(lukija.nextLine());
        System.out.println("Valitse pakettiin Levy: ");
        int idSTRG = Integer.valueOf(lukija.nextLine());
        System.out.println("Valitse pakettiin Kotelo: ");
        int idCASE = Integer.valueOf(lukija.nextLine());
        
        // Valmistetaan query millä kysytyt tuotteet lisätään pakettiin
        
        String querypktADD = "INSERT INTO paketit (mb_id, cpu_id, pwr_id, ram_id, gpu_id, strg_id, case_id)"
                            + "VALUES ('"+idMB+"','"+idCPU+"','"+idPWR+"','"+idRAM+"','"+idGPU+"','"+idSTRG+"','"+idCASE+"');";
        
        // ja tässä paketti lisätään tietokantaan.
        
        try (Statement pktAdd = conn.createStatement()) {
            int rs = pktAdd.executeUpdate(querypktADD);
            System.out.println("Paketin lisäys tietokantaan onnistui!");
        } catch (SQLException e) {
            System.out.println("ERROR: " + e);
        }
    }
    
    public static void muuta_paketti(Connection conn, Scanner lukija) {
    
        // Kysytään mitä pakettia halutaan muokata
        
        System.out.println("Valitse muokattavan tietokone paketin ID:");
        int pktValinta = Integer.valueOf(lukija.nextLine());
        
        // Kysytään uusien komponenttien id:t
        
        System.out.println("Valitse pakettiin Emolevy: ");
        int idMB = Integer.valueOf(lukija.nextLine());

        System.out.println("Valitse pakettiin Prosessori: ");
        int idCPU = Integer.valueOf(lukija.nextLine());

        System.out.println("Valitse pakettiin Virtalähde: ");
        int idPWR = Integer.valueOf(lukija.nextLine());

        System.out.println("Valitse pakettiin Muisti: ");
        int idRAM = Integer.valueOf(lukija.nextLine());

        System.out.println("Valitse pakettiin Näytönohjain: ");
        int idGPU = Integer.valueOf(lukija.nextLine());

        System.out.println("Valitse pakettiin Levy: ");
        int idSTRG = Integer.valueOf(lukija.nextLine());

        System.out.println("Valitse pakettiin Kotelo: ");
        int idCASE = Integer.valueOf(lukija.nextLine());
        
        // Uudet tiedot päivitetään tietokantaan
        
        String querypktUPDATE = "UPDATE paketit SET mb_id = '"+idMB+"', cpu_id = '"+idCPU+"', pwr_id = '"+idPWR+"', ram_id = '"+idRAM+"', gpu_id = '"+idGPU+"', strg_id = '"+idSTRG+"', case_id = '"+idCASE+"' WHERE id = '"+pktValinta+"'";
        try (Statement caseAdd = conn.createStatement()) {
            int rs2 = caseAdd.executeUpdate(querypktUPDATE);
            System.out.println("Paketin tietojen päivitys tietokantaan onnistui!");
        } catch (SQLException e) {
            System.out.println("ERROR: " + e);
        }
    }
    
    public static void poista_paketti(Connection conn, Scanner lukija) {
        
        // Valitaan minkä paketin haluaa poistaa
        
        System.out.println("Valitse paketin ID, jonka haluat poistaa.");
        int pktValinta = Integer.valueOf(lukija.nextLine());

        String querypktDEL = "DELETE FROM paketit WHERE id = '"+pktValinta+"'";
        
        // Varmistetaan että käyttäjä varmasti haluaa poistaa paketin
        
        System.out.println("Oletko varma että haluat poistaa Paketin, jonka ID on: " + pktValinta + "?");
        System.out.println("1. Kyllä, poista tuote");
        System.out.println("2. En, peruuttaa takaisin päävalikkoon");
        int poistoValinta = Integer.valueOf(lukija.nextLine());
        if (poistoValinta == 1) {
            try (Statement mbAdd = conn.createStatement()) {
            int rs = mbAdd.executeUpdate(querypktDEL);
            System.out.println("Paketin poisto onnistui!");
        } catch (SQLException e) {
            System.out.println("ERROR: " + e);
        }
        } else {
            System.out.println("Peruutetaan...");
        }
    }
    public static void paketin_hinta(Connection conn, Scanner lukija) {
        
        // Käyttäjä valitsee paketin jonka hinnan haluaa nähdä
        
        System.out.println("Valitse paketin ID.");
        int pktValinta = Integer.valueOf(lukija.nextLine());
        
        // SQL koodi jolla haetaan tuotteiden hinnat
        
        String priceQuery = "SELECT mb.hinta, cpu.hinta, pwr.hinta, ram.hinta, gpu.hinta, strg.hinta, cse.hinta "
                + "FROM paketit AS pkt "
                + "LEFT JOIN emolevyt AS mb ON pkt.mb_id = mb.id "
                + "LEFT JOIN prosessorit AS cpu ON pkt.cpu_id = cpu.id "
                + "LEFT JOIN powerit AS pwr ON pkt.pwr_id = pwr.id "
                + "LEFT JOIN muisti AS ram ON pkt.ram_id = ram.id "
                + "LEFT JOIN naytonohjaimet AS gpu ON pkt.gpu_id = gpu.id "
                + "LEFT JOIN levyt AS strg ON pkt.strg_id = strg.id "
                + "LEFT JOIN kotelot AS cse ON pkt.case_id = cse.id "
                + "WHERE pkt.id = '" + pktValinta + "'";
        
        try (Statement pktShow = conn.createStatement()) {
            ResultSet rs = pktShow.executeQuery(priceQuery);
            rs.next();
            
            // SQL:llä haetut hinnat muutetaan muuttujiksi
            
            int hintaMB = rs.getInt("mb.hinta");
            int hintaCPU = rs.getInt("cpu.hinta");
            int hintaPWR = rs.getInt("pwr.hinta");
            int hintaRAM = rs.getInt("ram.hinta");
            int hintaGPU = rs.getInt("gpu.hinta");
            int hintaSTRG = rs.getInt("strg.hinta");
            int hintaCASE = rs.getInt("cse.hinta");

            int kokoHinta = hintaMB + hintaCPU + hintaPWR + hintaRAM + hintaGPU + hintaSTRG + hintaCASE;
            
            // Tuotteitten hinnat tulostetaan
            
            System.out.println("Paketin kokonais-hinta: " + kokoHinta);
            System.out.println("");
            System.out.println("Emolevy\tProses.\tPoweri\tGPU\tMuisti\tLevy\tKotelo");
            System.out.println(hintaMB + "\t" + hintaCPU + "\t" + hintaPWR + "\t" + hintaGPU + "\t" + hintaRAM + "\t" + hintaSTRG + "\t" + hintaCASE);
            rs.close();
        } catch (SQLException e) {
            System.out.println("ERROR: " + e);
        }
    }
}