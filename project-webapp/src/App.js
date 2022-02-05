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
import EditSurname from "./Components/EditSurname";
import EditPass from "./Components/EditPass";
import AddProject from "./Components/AddProject";
import StudentList from "./Components/StudentList";
import AddStudent from "./Components/AddStudent";
import AddLecturer from "./Components/AddLecturer";
import StudentsInProjectList from "./Components/StudentsInProjectList";
import AddStudentToProject from "./Components/AddStudentToProject";
import TaskList from "./Components/TaskList";
import AddTask from "./Components/AddTask";
import EditTask from "./Components/EditTask";
import EditKanbanToDo from "./Components/EditKanbanToDo";
import EditKanbanNew from "./Components/EditKanbanNew";
import EditKanbanDoing from "./Components/EditKanbanDoing";
import EditKanbanCompleted from "./Components/EditKanbanCompleted";
import EditTaskName from "./Components/EditTaskName";
import EditTaskDescription from "./Components/EditTaskDescription";
import ProfileStudent from "./Components/ProfileStudent";
import EditNrIndex from "./Components/EditNrIndex";
import ProjectListStudent from "./Components/ProjectListStudent";
import EditProject from "./Components/EditProject";
import EditProjectName from "./Components/EditProjectName";
import EditProjectDescription from "./Components/EditProjectDescription";
import EndProject from "./Components/EndProject";
import WelcomeLecturer from "./Components/WelcomeLecturer";
import WelcomeStudent from "./Components/WelcomeStudent";
import DeleteProject from "./Components/DeleteProject";
import DeleteTask from "./Components/DeleteTask";
import DeleteStudent from "./Components/DeleteStudent";
import DeleteStudentFromProject from "./Components/DeleteStudentFromProject";


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
            <Route path={'/student/editPass'} exact component={NavigationBarStudent}/>
            <Route path={'/student/editName'} exact component={NavigationBarStudent}/>
            <Route path={'/student/editSurname'} exact component={NavigationBarStudent}/>
            <Route path={'/student/editNrIndex'} exact component={NavigationBarStudent}/>
            <Route path={'/student/projects/taskList'} exact component={NavigationBarStudent}/>
            <Route path={'/student/projects/taskList/addTask'} exact component={NavigationBarStudent}/>
            <Route path={'/student/projects/taskList/editTask'} exact component={NavigationBarStudent}/>
            <Route path={'/student/projects/taskList/editTask/editTaskName'} exact component={NavigationBarStudent}/>
            <Route path={'/student/projects/taskList/editTask/editTaskDescription'} exact component={NavigationBarStudent}/>
            <Route path={'/student/projects/editProject'} exact component={NavigationBarStudent}/>
            <Route path={'/student/projects/editProject/editProjectName'} exact component={NavigationBarStudent}/>
            <Route path={'/student/projects/editProject/editProjectDescription'} exact component={NavigationBarStudent}/>


            <Route path={'/lecturer'} exact component={NavigationBarLecturer}/>
            <Route path={'/lecturer/profile'} exact component={NavigationBarLecturer}/>
            <Route path={'/lecturer/projects'} exact component={NavigationBarLecturer}/>
            <Route path={'/lecturer/newLecturer'} exact component={NavigationBarLecturer}/>
            <Route path={'/lecturer/studentList'} exact component={NavigationBarLecturer}/>
            <Route path={'/lecturer/editName'} exact component={NavigationBarLecturer}/>
            <Route path={'/lecturer/editSurname'} exact component={NavigationBarLecturer}/>
            <Route path={'/lecturer/editPass'} exact component={NavigationBarLecturer}/>
            <Route path={'/lecturer/projects/addProject'} exact component={NavigationBarLecturer}/>
            <Route path={'/lecturer/studentList/addStudent'} exact component={NavigationBarLecturer}/>
            <Route path={'/lecturer/projects/studentList'} exact component={NavigationBarLecturer}/>
            <Route path={'/lecturer/projects/taskList'} exact component={NavigationBarLecturer}/>
            <Route path={'/lecturer/projects/studentList/addStudentToProject'} exact component={NavigationBarLecturer}/>
            <Route path={'/lecturer/projects/taskList/addTask'} exact component={NavigationBarLecturer}/>
            <Route path={'/lecturer/projects/taskList/editTask'} exact component={NavigationBarLecturer}/>
            <Route path={'/lecturer/projects/taskList/editTask/editTaskName'} exact component={NavigationBarLecturer}/>
            <Route path={'/lecturer/projects/taskList/editTask/editTaskDescription'} exact component={NavigationBarLecturer}/>
            <Route path={'/lecturer/projects/editProject'} exact component={NavigationBarLecturer}/>
            <Route path={'/lecturer/projects/editProject/editProjectName'} exact component={NavigationBarLecturer}/>
            <Route path={'/lecturer/projects/editProject/editProjectDescription'} exact component={NavigationBarLecturer}/>

            <Route path={'/logout'} exact component={Logout}/>
            <p></p>
            <Container>
                <Row>
                    <Col lg={30} style={marginTop}>
                        <Switch >
                            <Route path={'/'} exact component={Welcome}/>
                            <Route path={'/loginStudent'} exact component={LoginStudent}/>
                            <Route path={'/loginLecturer'} exact component={LoginLecturer}/>

                            <Route path={'/student'} exact component={WelcomeStudent}/>
                            <Route path={'/student/profile'} exact component={ProfileStudent}/>
                            <Route path={'/student/profile'} exact component={ProfileStudent}/>
                            <Route path={'/student/editPass'} exact component={EditPass}/>
                            <Route path={'/student/editName'} exact component={EditName}/>
                            <Route path={'/student/editSurname'} exact component={EditSurname}/>
                            <Route path={'/student/editNrIndex'} exact component={EditNrIndex}/>
                            <Route path={'/student/projects'} exact component={ProjectListStudent}/>
                            <Route path={'/student/projects/taskList'} exact component={TaskList}/>
                            <Route path={'/student/projects/taskList/addTask'} exact component={AddTask}/>
                            <Route path={'/student/projects/taskList/editTask'} exact component={EditTask}/>
                            <Route path={'/student/projects/taskList/editTask/kanbanTODO'} exact component={EditKanbanToDo}/>
                            <Route path={'/student/projects/taskList/editTask/kanbanNEW'} exact component={EditKanbanNew}/>
                            <Route path={'/student/projects/taskList/editTask/kanbanDOING'} exact component={EditKanbanDoing}/>
                            <Route path={'/student/projects/taskList/editTask/kanbanCOMPLETED'} exact component={EditKanbanCompleted}/>
                            <Route path={'/student/projects/taskList/editTask/editTaskName'} exact component={EditTaskName}/>
                            <Route path={'/student/projects/taskList/editTask/editTaskDescription'} exact component={EditTaskDescription}/>
                            <Route path={'/student/projects/editProject'} exact component={EditProject}/>
                            <Route path={'/student/projects/editProject/editProjectName'} exact component={EditProjectName}/>
                            <Route path={'/student/projects/editProject/editProjectDescription'} exact component={EditProjectDescription}/>
                            <Route path={'/student/projects/editProject/endProject'} exact component={EndProject}/>
                            <Route path={'/student/projects/taskList/deleteTask'} exact component={DeleteTask}/>


                            <Route path={'/lecturer'} exact component={WelcomeLecturer}/>
                            <Route path={'/lecturer/projects'} exact component={ProjectListLecturer}/>
                            <Route path={'/lecturer/profile'} exact component={ProfilLecturer}/>
                            <Route path={'/lecturer/editName'} exact component={EditName}/>
                            <Route path={'/lecturer/editSurname'} exact component={EditSurname}/>
                            <Route path={'/lecturer/editPass'} exact component={EditPass}/>
                            <Route path={'/lecturer/projects/addProject'} exact component={AddProject}/>
                            <Route path={'/lecturer/studentList'} exact component={StudentList}/>
                            <Route path={'/lecturer/studentList/addStudent'} exact component={AddStudent}/>
                            <Route path={'/lecturer/newLecturer'} exact component={AddLecturer}/>
                            <Route path={'/lecturer/projects/studentList'} exact component={StudentsInProjectList}/>
                            <Route path={'/lecturer/projects/studentList/addStudentToProject'} exact component={AddStudentToProject}/>
                            <Route path={'/lecturer/projects/taskList'} exact component={TaskList}/>
                            <Route path={'/lecturer/projects/taskList/addTask'} exact component={AddTask}/>
                            <Route path={'/lecturer/projects/taskList/editTask'} exact component={EditTask}/>
                            <Route path={'/lecturer/projects/taskList/editTask/kanbanTODO'} exact component={EditKanbanToDo}/>
                            <Route path={'/lecturer/projects/taskList/editTask/kanbanNEW'} exact component={EditKanbanNew}/>
                            <Route path={'/lecturer/projects/taskList/editTask/kanbanDOING'} exact component={EditKanbanDoing}/>
                            <Route path={'/lecturer/projects/taskList/editTask/kanbanCOMPLETED'} exact component={EditKanbanCompleted}/>
                            <Route path={'/lecturer/projects/taskList/editTask/editTaskName'} exact component={EditTaskName}/>
                            <Route path={'/lecturer/projects/taskList/editTask/editTaskDescription'} exact component={EditTaskDescription}/>
                            <Route path={'/lecturer/projects/editProject'} exact component={EditProject}/>
                            <Route path={'/lecturer/projects/editProject/editProjectName'} exact component={EditProjectName}/>
                            <Route path={'/lecturer/projects/editProject/editProjectDescription'} exact component={EditProjectDescription}/>
                            <Route path={'/lecturer/projects/editProject/endProject'} exact component={EndProject}/>
                            <Route path={'/lecturer/projects/deleteProject'} exact component={DeleteProject}/>
                            <Route path={'/lecturer/projects/taskList/deleteTask'} exact component={DeleteTask}/>
                            <Route path={'/lecturer/studentList/deleteStudent'} exact component={DeleteStudent}/>
                            <Route path={'/lecturer/projects/studentList/deleteStudent'} exact component={DeleteStudentFromProject}/>

                        </Switch >
                    </Col>
                </Row>

            </Container>

        </Router>
      </div>
  );
}

export default App;
