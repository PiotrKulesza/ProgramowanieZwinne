import {BrowserRouter as Router,Route,Switch } from 'react-router-dom'
import {Col, Container, Row} from "react-bootstrap";

import './App.css';

import NavigationBar from "./Components/NavigationBar";
import Welcome from "./Components/Welcome";
import LoginStudent from "./Components/LoginStudent";




function App() {

    const marginTop ={
        marginTop:"20px"
    };

  return (
      <div className="bg-black grid-container" >
        <Router>

            <Route path={'/'} exact component={NavigationBar}/>
            <Route path={'/loginStudent'} exact component={NavigationBar}/>
            <p></p>
            <Container>
                <Row>
                    <Col lg={30} style={marginTop}>
                        <Switch >
                            <Route path={'/'} exact component={Welcome}/>
                            <Route path={'/loginStudent'} exact component={LoginStudent}/>
                        </Switch >
                    </Col>
                </Row>

            </Container>

        </Router>
      </div>
  );
}

export default App;
