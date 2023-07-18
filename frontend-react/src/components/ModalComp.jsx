import {
  Modal,
  ModalOverlay,
  ModalContent,
  ModalHeader,
  ModalFooter,
  ModalBody,
  ModalCloseButton,
  Button,
  FormControl,
  FormLabel,
  Input,
  Box,
  Select,
} from "@chakra-ui/react";
import { useState, useEffect } from "react";
import axios from "axios";

const ModalComp = ({ data, setData, dataEdit, isOpen, onClose }) => {
  const [dataTransferencia, setDataTransferencia] = useState(
    dataEdit.dataTransferencia || ""
  );
  const [valor, setValor] = useState(dataEdit.valor || "");
  const [tipo, setTipo] = useState(dataEdit.tipo || "");
  const [nomeOperador, setNomeOperador] = useState(dataEdit.nomeOperador || "");
  const [conta, setConta] = useState(dataEdit.conta || "");
  const [contas, setContas] = useState([]); // Adicione o estado contas

  useEffect(() => {
    setDataTransferencia(dataEdit.dataTransferencia || "");
    setValor(dataEdit.valor || "");
    setTipo(dataEdit.tipo || "");
    setNomeOperador(dataEdit.nomeOperador || "");
    setConta(dataEdit.conta || "");
  }, [dataEdit]);

  useEffect(() => {
    const fetchContas = async () => {
      try {
        const response = await axios.get("http://localhost:8080/api/conta");
        setContas(response.data);
      } catch (error) {
        console.error(error);
      }
    };

    if (isOpen) {
      fetchContas();
    }
  }, [isOpen]);

  const handleSave = async () => {
    if (!dataTransferencia || !valor || !tipo || !conta) return;

    try {
      if (Object.keys(dataEdit).length) {
        await axios.put(`http://localhost:8080/api/transferencia/${dataEdit.id}`, {
          dataTransferencia,
          valor,
          tipo,
          nomeOperador: nomeOperador || null,
          conta,
        });
        const updatedData = data.map((item) =>
          item.id === dataEdit.id
            ? { ...item, dataTransferencia, valor, tipo, nomeOperador, conta }
            : item
        );
        setData(updatedData);
      } else {
        await axios.post("http://localhost:8080/api/transferencia", {
          dataTransferencia,
          valor,
          tipo,
          nomeOperador: nomeOperador || null,
          conta,
        });
        const newData = {
          id: Date.now(),
          dataTransferencia,
          valor,
          tipo,
          nomeOperador,
          conta,
        };
        setData([...data, newData]);
      }
    } catch (error) {
      console.error(error);
    }

    onClose();
  };

  return (
    <>
      <Modal isOpen={isOpen} onClose={onClose}>
        <ModalOverlay />
        <ModalContent>
          <ModalHeader>Cadastro de Transferências</ModalHeader>
          <ModalCloseButton />
          <ModalBody>
            <FormControl display="flex" flexDir="column" gap={4}>
              <Box>
                <FormLabel>Data da Transferência</FormLabel>
                <Input
                  type="date"
                  value={dataTransferencia}
                  onChange={(e) => setDataTransferencia(e.target.value)}
                />
              </Box>
              <Box>
                <FormLabel>Valor</FormLabel>
                <Input
                  type="number"
                  value={valor}
                  onChange={(e) => setValor(e.target.value)}
                />
              </Box>
              <Box>
                <FormLabel>Tipo</FormLabel>
                <Input
                  type="text"
                  value={tipo}
                  onChange={(e) => setTipo(e.target.value)}
                />
              </Box>
              <Box>
                <FormLabel>Nome do operador</FormLabel>
                <Input
                  type="text"
                  value={nomeOperador}
                  onChange={(e) => setNomeOperador(e.target.value)}
                />
              </Box>
              <Box>
                <FormLabel>Conta</FormLabel>
                <Select
                  value={conta}
                  onChange={(e) => setConta(e.target.value)}
                >
                  {contas.map((conta) => (
                    <option key={conta.id} value={conta.id}>
                      {conta.nome_responsavel}
                    </option>
                  ))}
                </Select>
              </Box>
            </FormControl>
          </ModalBody>

          <ModalFooter justifyContent="start">
            <Button colorScheme="green" mr={3} onClick={handleSave}>
              SALVAR
            </Button>
            <Button colorScheme="red" onClick={onClose}>
              CANCELAR
            </Button>
          </ModalFooter>
        </ModalContent>
      </Modal>
    </>
  );
};

export default ModalComp;
