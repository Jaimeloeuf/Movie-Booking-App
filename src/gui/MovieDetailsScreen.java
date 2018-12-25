package gui;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import dataStorage.Genre;
import dataStorage.GenreVector;
import dataStorage.Movie;
import dataStorage.MovieVector;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

// Displays more details of the movie that the user selected in AvailMoviesScreen
public class MovieDetailsScreen extends JPanel{
	private static String movieTitle;
	private static Genre genre;
	private static MainFrame mainFrame;
	
	public MovieDetailsScreen(MainFrame mf) {
		mainFrame = mf; // Setting the reference to the constructor field
		setLayout(null);  // Used for the event handler, need this to detect user selections
		mainFrame.setTitle("Movie details");
		
		movieTitle = mainFrame.controller().getCurrentMovieSelection().getTitle();
		JLabel lblMovieTitle = new JLabel("Movie Title:	 " + movieTitle);
		lblMovieTitle.setBounds(10, 11, 284, 26);
		this.add(lblMovieTitle);

		JLabel lblBy = new JLabel("<html>By: " + mainFrame.controller().getCurrentMovieSelection().getDirector() + "</html>");
		lblBy.setBounds(272, 11, 74, 37);
		this.add(lblBy);
		
		JLabel lblGenres = new JLabel("<html>");
		for(int i = 0; i<MovieVector.getMovieWithTitle(movieTitle).getGenreIDVector().size(); i++)
		{
			genre = GenreVector.get(MovieVector.getMovieWithTitle(movieTitle).getGenreIDVector().get(i));
			lblGenres.setText(lblGenres.getText() + genre.getName() + " \"" + genre.getDescription() + " \"<br>");
		}
		lblGenres.setText(lblGenres.getText() + "</html>");
		lblGenres.setVerticalAlignment(SwingConstants.TOP);
		lblGenres.setHorizontalAlignment(SwingConstants.LEFT);
		lblGenres.setBounds(10, 45, 336, 60);
		lblGenres.setBorder(BorderFactory.createTitledBorder("Movie Genre:"));
		this.add(lblGenres);
		
		JLabel lblDescription = new JLabel("<html>"+mainFrame.controller().getCurrentMovieSelection().getDescription()+"</html>");
		lblDescription.setVerticalAlignment(SwingConstants.TOP);
		lblDescription.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescription.setBounds(10, 106, 336, 71);
		lblDescription.setBorder(BorderFactory.createTitledBorder("Movie Description:"));
		this.add(lblDescription);
		
		JLabel lblRatings = new JLabel("Ratings: "+ mainFrame.controller().getCurrentMovieSelection().getRatings());
		lblRatings.setBounds(10, 181, 106, 14);
		add(lblRatings);
		
		JButton btnBrowseMovies = new JButton("Back to Availble Movies");
		btnBrowseMovies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){mainFrame.showAvailMoviesScreen();}
		});
		btnBrowseMovies.setBounds(10, 206, 178, 37);
		this.add(btnBrowseMovies);
		
		JButton btnTimeSlot = new JButton("See Time and Seats");
		btnTimeSlot.addActionListener(new ActionListener() { // Ask user to login before showing current bookings
			public void actionPerformed(ActionEvent arg0){mainFrame.showTimeSelectionScreen();}
		});
		btnTimeSlot.setBounds(198, 206, 156, 37);
		this.add(btnTimeSlot);
	}
}