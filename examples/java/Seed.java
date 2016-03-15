import enums.*;
import errors.*;
import java.util.*;
import java.lang.Integer;
import java.lang.Boolean;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.List;
import java.lang.InterruptedException;
import java.util.Arrays;
import java.lang.Math;
public class Seed {

	public static void main (String[] args) {
		
		System.out.println("\nSEED EXAMPLE\n");
		ViZDoomGameJava game= new ViZDoomGameJava();
		
		// Choose scenario config file you wish to be watched by agent.
    		// Don't load two configs cause the second will overwrite the first one.
    		// Multiple config files are ok but combining these ones doesn't make much sense.

		//game.loadConfig("../config/deadly_corridor.cfg")
		//game.loadConfig("../config/defend_the_center.cfg")
		//game.loadConfig("../config/defend_the_line.cfg")
		//game.loadConfig("../config/health_gathering.cfg")
		//game.loadConfig("../config/my_way_home.cfg")
		//game.loadConfig("../config/predict_position.cfg")
		
		game.loadConfig("../config/basic.cfg");
    		game.setDoomEnginePath("../../bin/vizdoom");
    		
		// Sets path to doom2 iwad resource file which contains the actual doom game-> Default is "./doom2.wad".
	    	game.setDoomGamePath("../../scenarios/freedoom2.wad");
		//game.setDoomGamePath("../../scenarios/doom2.wad");

		game.setScreenResolution(ScreenResolution.RES_640X480);

		int seed = 1234;
		// Sets the seed. It could be after init as well.
		game.setSeed(seed);
		game.init();

		List<int[]> actions = new ArrayList<int[]>();
	    	actions.add(new int[] {1, 0, 1});
		actions.add(new int[] {0, 1, 1});
		actions.add(new int[] {0, 0, 1});

	    	Random ran = new Random();

		int episodes = 10;

		for (int i=0;i<episodes;i++){

			System.out.println("Episode #" + (i+1));
			game.newEpisode();

			while ( !game.isEpisodeFinished()){
				// Gets the state and possibly to something with it
				GameState s = game.getState();
				int[] img = s.imageBuffer;
				int[] gameVariables = s.gameVariables;


 				// Make random action and get reward
				double reward = game.makeAction(actions.get(ran.nextInt(3)));
		
		
				System.out.println("State #" + s.number);
				System.out.println("Action Reward: " + reward);
				System.out.println("Seed: " + game.getSeed());
				System.out.println("=====================");

			}
			System.out.println("Episode finished!");
			System.out.println("Summary reward: " + game.getSummaryReward());
			System.out.println("************************");
		}
 		// It will be done automatically in destructor but after close You can init it again with different settings.
		game.close();
	}

}
