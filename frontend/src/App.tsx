import {Todo} from "./Todo.ts";
import {useEffect, useState} from "react";
import axios from "axios";
import TodoColumn from "./TodoColumn.tsx";
import {allPossibleTodos} from "./TodoStatus.ts";

function App() {

    const [user, setUser] = useState<string | null | undefined>(undefined)
    const [todos, setTodos] = useState<Todo[]>()

    function fetchTodos() {
        axios.get("/api/todo")
            .then(response => {
                setTodos(response.data)
            })
    }

    useEffect(() => {
        loadUser()
            .then(() => {
                fetchTodos()
            })
    }, [])

    function login() {
        const host = window.location.host === 'localhost:5173' ? 'http://localhost:8080' : window.location.origin

        window.open(host + '/oauth2/authorization/github', '_self')
    }

    function logout() {
        const host = window.location.host === 'localhost:5173' ? 'http://localhost:8080' : window.location.origin

        window.open(host + '/logout', '_self')
    }

    const loadUser = () => {
        return axios.get('/api/auth/me')
            .then(response => {
                setUser(response.data)
            })
            .catch(error => {
                setUser(null)
                throw error
            })
    }

    return (
        <div>
            <h1>TODOs</h1>
            {!user && <button onClick={login}>Login</button>}
            {user && <button onClick={logout}>Logout</button>}
            <div className="page">
                {
                    todos
                        ? allPossibleTodos.map(status => {
                            const filteredTodos = todos.filter(todo => todo.status === status)
                            return <TodoColumn
                                status={status}
                                todos={filteredTodos}
                                onTodoItemChange={fetchTodos}
                                key={status}
                            />
                        })
                        : <p>Loading...</p>
                }
            </div>
        </div>
    )
}

export default App
