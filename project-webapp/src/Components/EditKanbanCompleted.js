import React from "react";
import axios from 'axios';
import {Card} from "react-bootstrap";

class EditKanbanCompleted extends React.Component{
    constructor(props) {
        super(props)
        const queryParams = new URLSearchParams(window.location.search);
        this.state = {
            projectId:queryParams.get('projectId'),
            taskId:queryParams.get('taskId'),
        };
    }


    componentDidMount() {

        axios({
            method:'put',
            url:'http://localhost:8080/updateTask?kanban=COMPLETED&taskId='+this.state.taskId,
        }).then(()=>{
                let user ='';
                if ('STUDENT' === localStorage.getItem('typeOfUser')) {
                    user = 'student';

                }else{
                    user = 'lecturer';

                }

                window.location = "/"+user+"/projects/taskList/editTask?projectId="+this.state.projectId
                    +"&taskId="+this.state.taskId;
            }
        )

    }

    render() {
        return (
            <Card className={"border border-dark bg-dark text-white"}>
            </Card>
        );
    }
}

export default EditKanbanCompleted;