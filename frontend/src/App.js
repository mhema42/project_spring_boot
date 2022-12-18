
import './App.css';
import ButtonAppBar from './components/ButtonAppBar';
import CreateUser from './components/CreateUser';
import Login from './components/Login';

function App() {
  return (
    <div className="App">
      <ButtonAppBar />
      <CreateUser />
      <Login />
    </div>
  );
}

export default App;
