package persistence;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Commenti;
import model.Esito;
import model.Utente;
import model.Video;


public class DBManager {
	
	private static DBManager instance = null;
	private List<Utente> utenti;
	private ArrayList<Video> video;
	private ArrayList<Video> piu_visti;
	private Utente utenteCorrente;
	
	static {
		try {
			Class.forName("org.postgresql.Driver").newInstance();
		} 
		catch (Exception e) {
			System.err.println("PostgresDAOFactory.class: failed to load MySQL JDBC driver\n"+e);
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:postgresql://rajje.db.elephantsql.com:5432/zurfvxcg", "zurfvxcg", "EclVsDJMQ6vJCPrW3cfRwNtt_FJQfCi-");
		//rajje.db.elephantsql.com zurfvxcg zurfvxcg, EclVsDJMQ6vJCPrW3cfRwNtt_FJQfCi-
		return connection;
	}
	
	public static DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}
	
	private DBManager() {
		utenti = new ArrayList<Utente>();	
		video = new ArrayList<Video>();
		piu_visti = new ArrayList<Video>();

	}
	
	public void inserisciUtente(Utente u) {
		getUtenteDAO().save(u);
	}

	public Utente login(String email, String password) {
		return getUtenteDAO().findByPrimaryKey(email,password);
	}
	
	public void aggiungiVideo(Video v) {
		getVideoDAO().save(v);
	}

	public ArrayList<Video> getVideo() {
		return getVideoDAO().findAll();
	}
	
	public ArrayList<Esito> getStorico(){
		return getEsitoDAO().findByPrimaryKey(utenteCorrente.getEmail());
	}
	
	public ArrayList<Video> getEsito(int id_esito){
		return getEsitoDAO().getEsito(utenteCorrente.getEmail(), id_esito);
	}
	
	public ArrayList<Video> getPreferiti() {
		return getPreferitiDAO().findByPrimaryKey(utenteCorrente.getEmail());
	}
	
	public void aggiungiAiPreferiti(Video video) {
		if(getPreferitiDAO().getVideo(video.getUrl(), utenteCorrente.getEmail()) == null)
			getPreferitiDAO().save(video);
		else
			getPreferitiDAO().delete(video);
	}
	
	public Boolean isPreferito(Video video) {
		if(getPreferitiDAO().getVideo(video.getUrl(),utenteCorrente.getEmail()) != null) {
			return true;
		}
		else
			return false;	
	}
	
	public Commenti getCommenti(String url_video) {
		return getCommentiDAO().findByPrimaryKey(url_video);
	}
	
	public void aggiungiCommento(String commento, String url) {
		try {
			String comm = new String(commento.getBytes(), "UTF-8");
			getCommentiDAO().save(comm, url);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public void aggiungiAlloStorico(Esito esito) {
		getEsitoDAO().save(esito);
	}
	
	public List<Utente> getUtenti() {
		return utenti;
	}

	public void eliminaVideo(String url){
		getVideoDAO().delete(url);
	}

	public ArrayList<Video> getPiuVisti() {
		
		try {
			
			int totaleVisualizzazioni = 0;
			piu_visti.clear();
			
			ArrayList<Video> video_nel_db = getVideo();
			
			for (Video video : video_nel_db) {
				totaleVisualizzazioni+= video.getVisualizzazioni();
			}
			
			int mediaVisualizzazioni = totaleVisualizzazioni / video_nel_db.size();
			
			for (Video video : video_nel_db) {
				if(video.getVisualizzazioni() > mediaVisualizzazioni) {
					if(!piu_visti.contains(video))
						piu_visti.add(video);
				}
			}
		}
		catch (ArithmeticException e) {
			return piu_visti;
		}
		finally {
			return piu_visti;
		}	
	}

	public Utente getUtenteCorrente() {
		return utenteCorrente;
	}

	public void setUtenteCorrente(Utente utenteCorrente) {
		this.utenteCorrente = utenteCorrente;
	}

	public VideoDAO getVideoDAO() {
		return new VideoDAO_JDBC();
	}

	public CategoriaDAO getCategoriaDAO() {
		return new CategoriaDAO_JDBC();
	}
	
	public CommentiDAO getCommentiDAO() {
		return new CommentiDAO_JDBC();
	}
	
	public PreferitiDAO getPreferitiDAO() {
		return new PreferitiDAO_JDBC();
	}

	public EsitoDAO getEsitoDAO() {
		return new EsitoDAO_JDBC();
	}

	public UtenteDAO getUtenteDAO() {
		return new UtenteDAO_JDBC();
	}
	
	public RisposteUtenteDAO getRispostaUtenteDAO() {
		return new RisposteUtenteDAO_JDBC();
	}


	public void eliminaVideoEsito(String url) {
		getEsitoDAO().delete(url);
	}
	
	public void eliminaVideoCommento(String url) {
		getCommentiDAO().delete(url);
	}
	
	public void eliminaVideoPreferito(String url) {
		getPreferitiDAO().deleteUrlPreferiti(url);
	}
	
	public void eliminaVideoCategoria(String url) {
		getCategoriaDAO().deleteUrlCategoria(url);
	}

	public void eliminaVideoRispostaUtente(String url) {
		getRispostaUtenteDAO().delete(url);
	}

	public void modificaVideo(Video v) {
		getVideoDAO().update(v);
	}

	public boolean esisteEmail(String email) {
		return getUtenteDAO().esisteUtente(email);
	}
}
