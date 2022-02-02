import React from "react";
import axios from 'axios';
import {Card,Table} from 'react-bootstrap';
import { FiEdit  } from 'react-icons/fi';

class ProfilLecturer extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            userId : '',
            userType:'',
            users: {}
        }
    }

    componentDidMount() {

        this.setState({userId:localStorage.getItem('loggedUser')})
        axios({
            method:'get',
            url:'http://localhost:8080/lecturer/'+localStorage.getItem('loggedUser'),
        }).then(response => response.data)
            .then((data) =>{
                this.setState({users: data});
            });
    }

    render() {
        return (
            <Card className={"border border-dark bg-dark text-white"}>
                <Table border hover striped variant={"dark"}>
                    <tbody>


                    <tr>
                        <td>Imię</td>
                        <td>{this.state.users.imie} <a href={"/lecturer/editName"}><FiEdit   /></a></td>
                    </tr>
                    <tr>
                        <td>Nazwisko</td>
                        <td>{this.state.users.nazwisko} <a href={"/lecturer/editSurname"}><FiEdit   /></a></td>
                    </tr>
                    <tr>
                        <td>Hasło</td>
                        <td>**** <a href={"/lecturer/editPass"}><FiEdit   /></a></td>
                    </tr>

                    </tbody>
                </Table>
            </Card>
        );
    }
}

export default ProfilLecturer;