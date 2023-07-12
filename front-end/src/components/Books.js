import axios from "axios";
import React, { useEffect, useState } from "react";
import styled from '@emotion/styled';
import {TableBody, TableCell, tableCellClasses, TableRow } from '@mui/material';


const Books = () => {
    const tableHeader = ["ID", "TITLE", "AUTHOR", "DESCRIPTION"];

    const [books, setBooks] = useState([]);
    const [ setOpen] = useState(false);
    const [ setBookImg] = useState(undefined);

    const handleOpen = (bookImage) => {
        setOpen(true);
        setBookImg(bookImage);
    }

    useEffect(() => {
        axios.get("http://localhost:8084/reader/allBooks").then(response => {
            console.log(response);
            setBooks(response.data);
        });
    }, []);

    const StyledTableCell = styled(TableCell)(() => ({
        [`&.${tableCellClasses.head}`]: {
            padding: '50px',
            borderBottom: '10px solid',
        },
        [`&.${tableCellClasses.body}`]: {
            padding: '0px',
            borderBottom: '2px solid',
            overflow: 'hidden',
            textOverflow: 'ellipsis',
            whiteSpace: 'nowrap',
        },
    }));

    const StyledTableRow = styled(TableRow)(() => ({
        '&:nth-of-type(odd)': {
            backgroundColor: '#7eed84',
        },
        '&:nth-of-type(even)': {
            backgroundColor: '#c898f5',
        },
    }));

    return (
        <TableBody>
            {books.map((book) => (
                <StyledTableRow key={book.id}>
                    {Object.entries(book).map(([key, value]) => (
                        tableHeader.includes(key.toLocaleUpperCase()) &&
                        <StyledTableCell key={`${key}-${book.id}`} align="center" onClick={() => handleOpen(book.image)}>{value}</StyledTableCell>
                    ))}
                </StyledTableRow>
            ))}
        </TableBody>
    );
};

export default Books;