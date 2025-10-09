import React, { useState } from 'react';

interface CreateHardwareFormProps {
  onHardwareCreated: () => void;
  onClose: () => void;
}

const CreateHardwareForm: React.FC<CreateHardwareFormProps> = ({ onHardwareCreated, onClose }) => {
  const [description, setDescription] = useState('');
  const [quantity, setQuantity] = useState('');
  const [wholesalePrice, setWholesalePrice] = useState(0);
  const [retailPrice, setRetailPrice] = useState(0);
  const [wholesalePriceUnit, setWholesalePriceUnit] = useState('MWK');
  const [retailPriceUnit, setRetailPriceUnit] = useState('MWK');
  const [updatedBy, setUpdatedBy] = useState('user');
  const [location, setLocation] = useState('{"shelf": "A1"}');
  const [category, setCategory] = useState('test');
  const [file, setFile] = useState<File | null>(null);

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    const hardware = {
      description,
      quantity,
      wholesalePrice,
      retailPrice,
      wholesalePriceUnit,
      retailPriceUnit,
      updatedBy,
      location,
      category,
    };

    const formData = new FormData();
    formData.append('hardware', new Blob([JSON.stringify(hardware)], { type: 'application/json' }));
    if (file) {
      formData.append('file', file);
    }

    try {
      const response = await fetch('/api/hardware', {
        method: 'POST',
        body: formData,
      });

      if (response.ok) {
        const result = await response.json();
        console.log('Hardware created:', result);
        alert('Hardware created successfully!');
        onHardwareCreated(); // Notify parent to refresh list
        onClose(); // Close the modal
      } else {
        const errorText = await response.text();
        console.error('Failed to create hardware:', errorText);
        alert(`Failed to create hardware: ${errorText}`);
      }
    } catch (error) {
      console.error('Error:', error);
      alert(`An error occurred: ${error}`);
    }
  };

  return (
    <form onSubmit={handleSubmit} style={formStyle}>
      <h2>Create New Hardware</h2>
      <div style={formGroupStyle}>
        <label htmlFor="description" style={labelStyle}>Description:</label>
        <input
          id="description"
          type="text"
          value={description}
          onChange={(e) => setDescription(e.target.value)}
          style={inputStyle}
          required
        />
      </div>
      <div style={formGroupStyle}>
        <label htmlFor="quantity" style={labelStyle}>Quantity:</label>
        <input
          id="quantity"
          type="text"
          value={quantity}
          onChange={(e) => setQuantity(e.target.value)}
          style={inputStyle}
          required
        />
      </div>
      <div style={formGroupStyle}>
        <label htmlFor="wholesalePrice" style={labelStyle}>Wholesale Price:</label>
        <input
          id="wholesalePrice"
          type="number"
          value={wholesalePrice}
          onChange={(e) => setWholesalePrice(parseFloat(e.target.value))}
          style={inputStyle}
          required
        />
      </div>
      <div style={formGroupStyle}>
        <label htmlFor="retailPrice" style={labelStyle}>Retail Price:</label>
        <input
          id="retailPrice"
          type="number"
          value={retailPrice}
          onChange={(e) => setRetailPrice(parseFloat(e.target.value))}
          style={inputStyle}
          required
        />
      </div>
      <div style={formGroupStyle}>
        <label htmlFor="wholesalePriceUnit" style={labelStyle}>Wholesale Price Unit:</label>
        <input
          id="wholesalePriceUnit"
          type="text"
          value={wholesalePriceUnit}
          onChange={(e) => setWholesalePriceUnit(e.target.value)}
          style={inputStyle}
          required
        />
      </div>
      <div style={formGroupStyle}>
        <label htmlFor="retailPriceUnit" style={labelStyle}>Retail Price Unit:</label>
        <input
          id="retailPriceUnit"
          type="text"
          value={retailPriceUnit}
          onChange={(e) => setRetailPriceUnit(e.target.value)}
          style={inputStyle}
          required
        />
      </div>
      <div style={formGroupStyle}>
        <label htmlFor="updatedBy" style={labelStyle}>Updated By:</label>
        <input
          id="updatedBy"
          type="text"
          value={updatedBy}
          onChange={(e) => setUpdatedBy(e.target.value)}
          style={inputStyle}
          required
        />
      </div>
      <div style={formGroupStyle}>
        <label htmlFor="location" style={labelStyle}>Location (JSON String):</label>
        <input
          id="location"
          type="text"
          value={location}
          onChange={(e) => setLocation(e.target.value)}
          style={inputStyle}
          required
        />
      </div>
      <div style={formGroupStyle}>
        <label htmlFor="category" style={labelStyle}>Category:</label>
        <input
          id="category"
          type="text"
          value={category}
          onChange={(e) => setCategory(e.target.value)}
          style={inputStyle}
          required
        />
      </div>
      <div style={formGroupStyle}>
        <label htmlFor="file" style={labelStyle}>Image:</label>
        <input
          id="file"
          type="file"
          onChange={(e) => setFile(e.target.files ? e.target.files[0] : null)}
          style={inputStyle}
          required
        />
      </div>
      <div style={buttonGroupStyle}>
        <button type="submit" style={submitButtonStyle}>Create</button>
        <button type="button" onClick={onClose} style={cancelButtonStyle}>Cancel</button>
      </div>
    </form>
  );
};

const formStyle: React.CSSProperties = {
  display: 'flex',
  flexDirection: 'column',
  gap: '10px',
  padding: '20px',
  borderRadius: '8px',
  backgroundColor: 'white',
  boxShadow: '0 2px 10px rgba(0, 0, 0, 0.1)',
};

const formGroupStyle: React.CSSProperties = {
  display: 'flex',
  flexDirection: 'column',
};

const labelStyle: React.CSSProperties = {
  marginBottom: '5px',
  fontWeight: 'bold',
  color: '#333',
};

const inputStyle: React.CSSProperties = {
  padding: '8px',
  border: '1px solid #ddd',
  borderRadius: '4px',
  fontSize: '16px',
  color: 'black',
};

const buttonGroupStyle: React.CSSProperties = {
  display: 'flex',
  justifyContent: 'flex-end',
  gap: '10px',
  marginTop: '20px',
};

const submitButtonStyle: React.CSSProperties = {
  padding: '10px 20px',
  backgroundColor: '#007bff',
  color: 'white',
  border: 'none',
  borderRadius: '4px',
  cursor: 'pointer',
  fontSize: '16px',
  transition: 'background-color 0.2s',
};

const cancelButtonStyle: React.CSSProperties = {
  padding: '10px 20px',
  backgroundColor: '#6c757d',
  color: 'white',
  border: 'none',
  borderRadius: '4px',
  cursor: 'pointer',
  fontSize: '16px',
  transition: 'background-color 0.2s',
};

export default CreateHardwareForm;
