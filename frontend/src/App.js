
import './App.css';
import { setTokenForAuthentication } from './helpers/setTokenForAuthentication';
import Routes from './routes';

const token = localStorage.getItem("token");
if (token) {
  setTokenForAuthentication(token);
}

function App() {
  return (
    <Routes />
  );
}

export default App;
