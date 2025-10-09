import React from 'react';

interface HeaderProps {
  onOpenCreateHardwareModal: () => void;
}

const Header: React.FC<HeaderProps> = ({ onOpenCreateHardwareModal }) => {
  return (
    <header style={{ padding: '10px 20px', background: '#333', color: 'white', borderBottom: '1px solid #eee', display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
      <h2>Hardware Manager</h2>
      <button onClick={onOpenCreateHardwareModal} style={{ padding: '8px 15px', background: '#007bff', color: 'white', border: 'none', borderRadius: '5px', cursor: 'pointer' }}>
        Create New Hardware
      </button>
    </header>
  );
};

export default Header;
