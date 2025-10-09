import { useState, useEffect } from 'react';
import HardwareCard from './components/HardwareCard';
import Header from './components/Header';
import Modal from './components/Modal';
import CreateHardwareForm from './components/CreateHardwareForm';

interface Hardware {
  id: string;
  description: string;
  quantity: string;
  retailPrice: number;
  imageUrl: string;
}

function App() {
  const [hardwareList, setHardwareList] = useState<Hardware[]>([]);
  const [isModalOpen, setIsModalOpen] = useState(false);

  const fetchHardware = async () => {
    try {
      const response = await fetch('/api/hardware');
      if (response.ok) {
        const data = await response.json();
        console.log('Fetched hardware data:', data);
        setHardwareList(data);
      } else {
        console.error('Failed to fetch hardware');
      }
    } catch (error) {
      console.error('Error:', error);
    }
  };

  useEffect(() => {
    fetchHardware();
  }, []);

  const handleHardwareCreated = () => {
    fetchHardware(); // Re-fetch the list after a new hardware is created
  };

  return (
    <div>
      <Header onOpenCreateHardwareModal={() => setIsModalOpen(true)} />
      <div style={{ display: 'flex', flexWrap: 'wrap', padding: '20px' }}>
        {hardwareList.map((hardware) => (
          <HardwareCard key={hardware.id} hardware={hardware} />
        ))}
      </div>

      <Modal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)}>
        <CreateHardwareForm onHardwareCreated={handleHardwareCreated} onClose={() => setIsModalOpen(false)} />
      </Modal>
    </div>
  );
}

export default App;