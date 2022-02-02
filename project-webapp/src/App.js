import {BrowserRouter as Router,Route,Switch } from 'react-router-dom'
import {Col, Container, Row} from "react-bootstrap";

import './App.css';

import NavigationBar from "./Components/NavigationBar";
import NavigationBarStudent from "./Components/NavigationBarStudent";
import Welcome from "./Components/Welcome";
import LoginStudent from "./Components/LoginStudent";
import Logout from "./Components/Logout"
import LoginLecturer from "./Components/LoginLecturer";
import NavigationBarLecturer from "./Components/NavigationBarLecturer";
import ProjectListLecturer from "./Components/ProjectListLecturer";
import ProfilLecturer from "./Components/ProfilLecturer";
import EditName from "./Components/EditName";
import EditNazwisko from "./Components/EditSurname";
import EditPass from "./Components/EditPass";
import AddProject from "./Components/AddProject";
import StudentList from "./Components/StudentList";
import AddStudent from "./Components/AddStudent";
import AddLecturer from "./Components/AddLecturer";
import StudentsInProjectList from "./Components/StudentsInProjectList";
import AddStudentToProject from "./Components/AddStudentToProject";
import TaskListLecturer from "./Components/TaskListLecturer";



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
            <Route path={'/student/profile'} exact component={NavigationBarStudent}/>
            <Route path={'/student/projects'} exact component={NavigationBarStudent}/>


            <Route path={'/lecturer'} exact component={NavigationBarLecturer}/>
            <Route path={'/lecturer/profile'} exact component={NavigationBarLecturer}/>
            <Route path={'/lecturer/projects'} exact component={NavigationBarLecturer}/>
            <Route path={'/lecturer/newLecturer'} exact component={NavigationBarLecturer}/>
            <Route path={'/lecturer/studentList'} exact component={NavigationBarLecturer}/>
            <Route path={'/lecturer/editName'} exact component={NavigationBarLecturer}/>
            <Route path={'/lecturer/editSurname'} exact component={NavigationBarLecturer}/>
            <Route path={'/lecturer/editPass'} exact component={NavigationBarLecturer}/>
            <Route path={'/lecturer/editPass'} exact component={NavigationBarLecturer}/>
            <Route path={'/lecturer/projects/addProject'} exact component={NavigationBarLecturer}/>
            <Route path={'/lecturer/studentList/addStudent'} exact component={NavigationBarLecturer}/>
            <Route path={'/lecturer/projects/studentList'} exact component={NavigationBarLecturer}/>
            <Route path={'/lecturer/projects/taskList'} exact component={NavigationBarLecturer}/>
            <Route path={'/lecturer/projects/studentList/addStudentToProject'} exact component={NavigationBarLecturer}/>
            <Route path={'/lecturer/projects/taskLis/addTask'} exact component={NavigationBarLecturer}/>

            <Route path={'/logout'} exact component={Logout}/>
            <p></p>
            <Container>
                <Row>
                    <Col lg={30} style={marginTop}>
                        <Switch >
                            <Route path={'/'} exact component={Welcome}/>
                            <Route path={'/loginStudent'} exact component={LoginStudent}/>
                            <Route path={'/loginLecturer'} exact component={LoginLecturer}/>


                            <Route path={'/lecturer/projects'} exact component={ProjectListLecturer}/>
                            <Route path={'/lecturer/profile'} exact component={ProfilLecturer}/>
                            <Route path={'/lecturer/editName'} exact component={EditName}/>
                            <Route path={'/lecturer/editSurname'} exact component={EditNazwisko}/>
                            <Route path={'/lecturer/editPass'} exact component={EditPass}/>
                            <Route path={'/lecturer/projects/addProject'} exact component={AddProject}/>
                            <Route path={'/lecturer/studentList'} exact component={StudentList}/>
                            <Route path={'/lecturer/studentList/addStudent'} exact component={AddStudent}/>
                            <Route path={'/lecturer/newLecturer'} exact component={AddLecturer}/>
                            <Route path={'/lecturer/projects/studentList'} exact component={StudentsInProjectList}/>
                            <Route path={'/lecturer/projects/studentList/addStudentToProject'} exact component={AddStudentToProject}/>
                            <Route path={'/lecturer/projects/taskList'} exact component={TaskListLecturer}/>

                        </Switch >
                    </Col>
                </Row>

            </Container>

        </Router>
      </div>
  );
}

export default App;
