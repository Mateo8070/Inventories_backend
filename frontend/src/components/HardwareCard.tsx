import React from 'react';

interface Hardware {
  id: string;
  description: string;
  quantity: string;
  retailPrice: number;
  imageUrl: string;
}

interface HardwareCardProps {
  hardware: Hardware;
}

const HardwareCard: React.FC<HardwareCardProps> = ({ hardware }) => {
  console.log('HardwareCard prop:', hardware);
  return (
    <div style={{ border: '1px solid #ccc', borderRadius: '8px', padding: '16px', margin: '16px', maxWidth: '300px' }}>
      {hardware.imageUrl && <img src={hardware.imageUrl} alt={hardware.description} style={{ width: '100%', height: 'auto', borderRadius: '4px' }} />}
      <h3>{hardware.description}</h3>
      <p>Quantity: {hardware.quantity}</p>
      <p>Price: MWK {hardware.retailPrice}</p>
    </div>
  );
};

export default HardwareCard;