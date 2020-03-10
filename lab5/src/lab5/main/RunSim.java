package lab5.main;

import lab5.deds.EventQueue;
import lab5.deds.Simulator;
import lab5.deds.View;
import lab5.snabbköp.state.SnabbköpState;
import lab5.snabbköp.view.SnabbköpsView;

public class RunSim {

	public static void main(String[] args) {		
//	  EventQueue que = new EventQueue();
//        SnabbköpState state = new SnabbköpState(1234,1.0,2.0,3.0,0.5,1.0,2,5,10.00);
//        SnabbköpsView view = new SnabbköpsView(state);
//        new Simulator(state,que,view);  
        
	  EventQueue que = new EventQueue();
          SnabbköpState state = new SnabbköpState(13,3.0,0.35,0.6,0.6,0.9,2,7,8.00);
          SnabbköpsView view = new SnabbköpsView(state);
          new Simulator(state,que,view);  
	}
}
