import './App.css';
import CustomerLayout from './components/customer/CustomerLayout';
import ManagerLayout from './components/manager/ManagerLayout';
import { AuthProvider } from './AuthProvider';

function App() {
  return (
    <div className="App">
      <CustomerLayout />
      {/* <ManagerLayout /> */}
    </div>
  );
}

export default App;
