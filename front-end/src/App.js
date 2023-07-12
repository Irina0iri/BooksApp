import 'bootstrap/dist/css/bootstrap-grid.min.css';
import './App.css';
import {Routes, Route} from "react-router-dom";
import UserRegistration from "./components/UserRegistration";
import UserLogin from "./components/UserLogin";
// import NavBar from "./components/NavBar";
import FirstPage from "./components/FirstPage";
import Books from "./components/Books";

function App() {

  return (
    <div className="App">
        <h1> Welcome to books app </h1>
        <Routes>
            <Route path="/register" element={<UserRegistration message="Hello! This is my about component!"/>}
            />
            <Route path="/login" element={<UserLogin message="Hello! "/>}
            />
            <Route path="/firstPage" element={<FirstPage message="Hello! "/>}
            />
            <Route path="/books" element={<Books message="Hello! "/>}
            />
        </Routes>
    </div>
  );
}

export default App;
