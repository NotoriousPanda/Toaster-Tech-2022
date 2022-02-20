package frc.robot;
import java.util.List;
import java.util.Arrays;

/*
This is mostly a abstraction so when we inevitably use state machines we are not using a 
bunch of if/switch statements
*/

public class StateMachine {

    //this can be changed to fit our actual implementation when it comes around
    public interface StateCallback {
        void call();
    }

    public class State {
        String name;
        StateCallback callback;
  
        public State(String stateName, StateCallback callback) {
          this.name = stateName;
          this.callback = callback;
        }
  
        public void run() {
          this.callback.call();
        }
    }

    List<State> states;
    public byte currentState = 0;

    public StateMachine(State[] states) {
        this.states = Arrays.asList(states);
    }

    public void progressState() {
        if(this.currentState <= this.states.size()-1) {
          this.currentState++;
        }
    }

    public void update() {
        this.states.get(this.currentState).callback.call();
    }
}
