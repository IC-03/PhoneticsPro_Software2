import React, { useRef, useEffect } from "react";
import audiobtn from "../assets/images/sonido.png";
import keyboardstyle from "../assets/css/keyboard.css";

const WordAudioButton = ({ currentWord }) => {
  const audioRef = useRef(null);

  useEffect(() => {
    if (currentWord && audioRef.current) {
      audioRef.current.src = `${process.env.PUBLIC_URL}/audios/${currentWord}.wav`;
    }
  }, [currentWord]);

  const playAudio = () => {
    if (audioRef.current) {
      audioRef.current.play();
    }
  };

  return (
    <div
      className="p-2"
      id="game-words"
      style={{
        display: "flex",
        alignItems: "center",
        justifyContent: "center",
      }}
    >
      <button className={`${keyboardstyle.bttn} bttn`} onClick={playAudio}>
        <img
          src={audiobtn}
          alt="sonido"
          style={{ width: 28, height: 28 }}
        />
      </button>
      <span style={{ marginLeft: "8px" }}>{currentWord}</span>
      <audio ref={audioRef} />
    </div>
  );
};

export default WordAudioButton;