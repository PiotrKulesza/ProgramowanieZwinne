import {BrowserRouter as Router,Route,Switch } from 'react-router-dom'
import {Col, Container, Row} from "react-bootstrap";

import './App.css';

import NavigationBar from "./Components/NavigationBar";
import NavigationBarStudent from "./Components/NavigationBarStudent";
import Welcome from "./Components/Welcome";
import LoginStudent from "./Components/LoginStudent";
import Loggout from "./Components/Loggout"
import LoginLecturer from "./Components/LoginLecturer";
import NavigationBarLecturer from "./Components/NavigationBarLecturer";
import ProjectListLecturer from "./Components/ProjectListLecturer";
import ProfilLecturer from "./Components/ProfilLecturer";
import EditName from "./Components/EditName";





function App() {

    const marginTop ={
        marginTop:"20px"
    };

  return (
      <div className="bg-black grid-container" >
        <Router>

            <Route path={'/'} exact component={NavigationBar}/>
            <Route path={'/loginStudent'} exact component={NavigationBar}/>
            <Route path={'/LoginLecturer'} exact component={NavigationBar}/>
            <Route path={'/student'} exact component={NavigationBarStudent}/>
            <Route path={'/student/profil'} exact component={NavigationBarStudent}/>
            <Route path={'/student/projekty'} exact component={NavigationBarStudent}/>
            <Route path={'/lecturer'} exact component={NavigationBarLecturer}/>
            <Route path={'/lecturer/profil'} exact component={NavigationBarLecturer}/>
            <Route path={'/lecturer/projekty'} exact component={NavigationBarLecturer}/>
            <Route path={'/lecturer/newLecturer'} exact component={NavigationBarLecturer}/>
            <Route path={'/lecturer/newStudent'} exact component={NavigationBarLecturer}/>
            <Route path={'/lecturer/editName'} exact component={NavigationBarLecturer}/>
            <Route path={'/loggout'} exact component={Loggout}/>
            <p></p>
            <Container>
                <Row>
                    <Col lg={30} style={marginTop}>
                        <Switch >
                            <Route path={'/'} exact component={Welcome}/>
                            <Route path={'/loginStudent'} exact component={LoginStudent}/>
                            <Route path={'/loginLecturer'} exact component={LoginLecturer}/>
                            <Route path={'/lecturer/projekty'} exact component={ProjectListLecturer}/>
                            <Route path={'/lecturer/profil'} exact component={ProfilLecturer}/>
                            <Route path={'/lecturer/editName'} exact component={EditName}/>
                        </Switch >
                    </Col>
                </Row>

            </Container>

        </Router>
      </div>
  );
}

export default App;
