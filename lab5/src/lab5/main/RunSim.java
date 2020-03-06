package lab5.main;

import lab5.deds.EventQueue;
import lab5.deds.Simulator;
import lab5.deds.View;
import lab5.snabbköp.state.SnabbköpState;

public class RunSim {

	public static void main(String[] args) {
		
		EventQueue que = new EventQueue();
        SnabbköpState state = new SnabbköpState(13,3.0,0.35,0.6,0.6,0.9,2,7,08.00);
        View view = new View(state);
        new Simulator(state,que,view);
	}
}
