import React, { useState } from 'react';

// ---------- MODAL COMPONENT ----------
interface ModalProps {
  isOpen: boolean;
  onClose: () => void;
  children: React.ReactNode;
}

export const Modal: React.FC<ModalProps> = ({ isOpen, onClose, children }) => {
  if (!isOpen) return null;

  return (
    <div style={overlayStyle}>
      <div style={modalContentStyle}>
        <button onClick={onClose} style={closeButtonStyle}>&times;</button>
        {children}
      </div>
    </div>
  );
};

// ---------- INPUT COMPONENT ----------
interface InputProps extends React.InputHTMLAttributes<HTMLInputElement> {}

export const CoolTextField: React.FC<InputProps> = (props) => {
  const [isFocused, setIsFocused] = useState(false);

  return (
    <input
      {...props}
      style={{ ...inputStyle, ...(isFocused ? inputFocusStyle : {}) }}
      onFocus={() => setIsFocused(true)}
      onBlur={() => setIsFocused(false)}
    />
  );
};

// ---------- BUTTON COMPONENT ----------
interface ButtonProps extends React.ButtonHTMLAttributes<HTMLButtonElement> {}

export const CoolButton: React.FC<ButtonProps> = (props) => {
  return <button style={buttonStyle} {...props} />;
};

// ---------- STYLES ----------
const overlayStyle: React.CSSProperties = {
  position: 'fixed',
  top: 0,
  left: 0,
  right: 0,
  bottom: 0,
  backgroundColor: 'rgba(62,56,56,0.6)',
  display: 'flex',
  alignItems: 'center',
  justifyContent: 'center',
  zIndex: 1000,
  backdropFilter: 'blur(4px)',
};

const modalContentStyle: React.CSSProperties = {
  backgroundColor: '#e1e1e8',
  padding: '30px',
  borderRadius: '12px',
  position: 'relative',
  minWidth: '320px',
  maxWidth: '600px',
  width: '90%',
  boxShadow: '0 10px 30px rgba(0,0,0,0.5)',
  color: '#f0f0f5',
};

const closeButtonStyle: React.CSSProperties = {
  position: 'absolute',
  top: '15px',
  right: '15px',
  background: 'none',
  border: 'none',
  fontSize: '28px',
  fontWeight: 'bold',
  cursor: 'pointer',
  color: '#aaa',
  transition: 'color 0.2s',
};

const inputStyle: React.CSSProperties = {
  width: '100%',
  padding: '12px 15px',
  margin: '10px 0',
  borderRadius: '8px',
  border: '2px solid #444',
  backgroundColor: '#ededef',
  color: '#f0f0f5',
  fontSize: '16px',
  outline: 'none',
  transition: 'all 0.25s ease-in-out',
  boxShadow: 'inset 0 2px 4px rgba(0,0,0,0.3)',
};

const inputFocusStyle: React.CSSProperties = {
  borderColor: '#6c63ff',
  boxShadow: '0 0 8px rgba(108, 99, 255, 0.6)',
  color: '#fff',
};

const buttonStyle: React.CSSProperties = {
  padding: '12px 25px',
  marginTop: '15px',
  borderRadius: '8px',
  border: 'none',
  background: 'linear-gradient(90deg, #6c63ff, #a29bfe)',
  color: '#fff',
  fontSize: '16px',
  cursor: 'pointer',
  transition: 'all 0.2s ease-in-out',
};

export default Modal;
