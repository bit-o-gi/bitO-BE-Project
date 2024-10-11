import tw from "tailwind-styled-components";

const Main = () => {
  return (
    <Section>
      <Title>시작한지</Title>
      <Countdown>100일</Countdown>
    </Section>
  );
};

const Title = tw.h1`
    text-4xl
    font-bold
    p-4
`;

const Section = tw.section`
    mt-8
    p-8
    bg-white
    rounded-lg
    shadow-lg
    text-center
    max-w-md
    w-full
`;

const Countdown = tw.h3`
    text-2xl
    font-semibold
    text-pink-500
`;

export default Main;
