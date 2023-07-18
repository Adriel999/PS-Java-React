import { EditIcon, DeleteIcon, SearchIcon, AddIcon } from "@chakra-ui/icons";
import {
  Box,
  Flex,
  Button,
  useDisclosure,
  Table,
  Thead,
  Tr,
  Th,
  Tbody,
  Td,
  useBreakpointValue,
  Input,
  HStack,
  FormControl,
  FormLabel,
} from "@chakra-ui/react";
import React, { useEffect, useState } from "react";
import { format } from "date-fns"; // Importe a função de formatação de data
import ModalComp from "./components/ModalComp";
import axios from "axios";

const App = () => {
  const { isOpen, onOpen, onClose } = useDisclosure();
  const [data, setData] = useState([]);
  const [dataEdit, setDataEdit] = useState({});
  const [filterStartDate, setFilterStartDate] = useState("");
  const [filterEndDate, setFilterEndDate] = useState("");
  const [filterOperator, setFilterOperator] = useState("");
  const isMobile = useBreakpointValue({
    base: true,
    lg: false,
  });

  useEffect(() => {
    const fetchData = async () => {
      try {
        let url = "http://localhost:8080/api/transferencia";

        if (filterStartDate && filterEndDate) {
          url += `?startDate=${filterStartDate}&endDate=${filterEndDate}`;
        } else if (filterOperator) {
          url = `http://localhost:8080/api/transferencia/operador/${filterOperator}`; 
        }

        const response = await axios.get(url);
        setData(response.data);
      } catch (error) {
        console.log(error);
      }
    };

    fetchData();
  }, [filterStartDate, filterEndDate, filterOperator]);

  const handleRemove = async (id) => {
    try {
      await axios.delete(`http://localhost:8080/api/transferencia/${id}`);
      const updatedData = data.filter((item) => item.id !== id);
      setData(updatedData);
    } catch (error) {
      console.error(error);
    }
  };

  const handleSearch = () => {
    if ((!filterStartDate && filterEndDate) || (filterStartDate && !filterEndDate)) {
      alert("Por favor, preencha ambas as datas para filtrar corretamente.");
      setFilterStartDate("");
      setFilterEndDate("");
    }
  };

  const formatDate = (date) => {
    return format(new Date(date), "dd/MM/yyyy"); // Formata a data como "dd/mm/aaaa"
  };

  const formatCurrency = (value) => {
    return value.toLocaleString("pt-BR", { style: "currency", currency: "BRL", minimumFractionDigits: 2 });
  };
  
  return (
    <Flex
      h="100vh"
      align="center"
      justify="center"
      fontSize="13px"
      fontFamily="poppins"
    >
      <Box maxW={1000} w="100%" h="100vh" py={10} px={2}>
        <HStack spacing={2} mb={4}>
          <FormControl>
            <FormLabel>Data de início</FormLabel>
            <Input
              type="date"
              value={filterStartDate}
              onChange={(e) => setFilterStartDate(e.target.value)}
            />
          </FormControl>
          <FormControl>
            <FormLabel>Data de fim</FormLabel>
            <Input
              type="date"
              value={filterEndDate}
              onChange={(e) => setFilterEndDate(e.target.value)}
            />
          </FormControl>
          <FormControl>
            <FormLabel>Nome do Operador</FormLabel>
            <Input
              type="text"
              value={filterOperator}
              onChange={(e) => setFilterOperator(e.target.value)}
            />
          </FormControl>
          <Button colorScheme="green" onClick={handleSearch}>
            <SearchIcon />
          </Button>
        </HStack>
        <Button colorScheme="blue" onClick={() => [setDataEdit({}), onOpen()]}>
          <AddIcon />
        </Button>
        <Box overflowY="auto" height="100%">
          <Table mt="6">
            <Thead>
              <Tr>
                <Th maxW={isMobile ? 5 : 100} fontSize="13px">
                  Data de Transferência
                </Th>
                <Th maxW={isMobile ? 5 : 100} fontSize="13px">
                  Valor
                </Th>
                <Th maxW={isMobile ? 5 : 100} fontSize="13px">
                  Tipo
                </Th>
                <Th maxW={isMobile ? 5 : 100} fontSize="13px">
                  Nome do Operador
                </Th>
                <Th maxW={isMobile ? 5 : 100} fontSize="13px">
                  Titular da Conta
                </Th>
                <Th p={0}></Th>
                <Th p={0}></Th>
              </Tr>
            </Thead>
            <Tbody>
              {data.map(({ id, dataTransferencia, valor, tipo, nomeOperador, contaId }) => (
                <Tr key={id} cursor="pointer" _hover={{ bg: "gray.100" }}>
                  <Td maxW={isMobile ? 5 : 100}>{formatDate(dataTransferencia)}</Td> {/* Formatação da data */}
                  <Td maxW={isMobile ? 5 : 100}>{formatCurrency(valor)}</Td> {/* Formatação do valor*/}
                  <Td maxW={isMobile ? 5 : 100}>{tipo}</Td>
                  <Td maxW={isMobile ? 5 : 100}>{nomeOperador}</Td>
                  <Td maxW={isMobile ? 5 : 100}>{contaId.nomeResponsavel}</Td>
                  <Td p={0}>
                    <EditIcon
                      fontSize={10}
                      onClick={() => [
                        setDataEdit({ id, dataTransferencia, valor, tipo, nomeOperador, contaId }),
                        onOpen(),
                      ]}
                    />
                  </Td>
                  <Td p={0}>
                    <DeleteIcon
                      fontSize={10}
                      onClick={() => handleRemove(id)}
                    />
                  </Td>
                </Tr>
              ))}
            </Tbody>
          </Table>
        </Box>
      </Box>
      {isOpen && (
        <ModalComp
          isOpen={isOpen}
          onClose={onClose}
          data={data}
          setData={setData}
          dataEdit={dataEdit}
          setDataEdit={setDataEdit}
        />
      )}
    </Flex>
  );
};

export default App;
