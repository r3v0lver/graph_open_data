CREATE TABLE graph (
                       id VARCHAR(32) NOT NULL PRIMARY KEY,
                       vNum INT NOT NULL,
                       adjMatrix INTEGER[][] NOT NULL,
                       simpleGraph BOOLEAN,
                       chromaticNumber INT,
                       isBipartite BOOLEAN,
                       edgeCount INT,
                       connectedComponents INT,
                       density FLOAT,
                       maxVertexDegree INT
);

CREATE TABLE coloring (
                          id SERIAL PRIMARY KEY,
                          graph_id VARCHAR(32) REFERENCES graph(id) ON DELETE CASCADE,
                          coloring INTEGER[]
);


INSERT INTO graph (id, vNum, adjMatrix, simpleGraph, chromaticNumber, isBipartite, edgeCount, connectedComponents, density, maxVertexDegree) VALUES
                                                                                                                                                 ('graph_1', 3, '{{1, 1, 0}, {1, 1, 1}, {0, 1, 1}}', TRUE, 2, TRUE, 2, 1, 0.6667, 2),
                                                                                                                                                 ('graph_2', 3, '{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}}', TRUE, 3, FALSE, 3, 1, 1.0, 2),
                                                                                                                                                 ('graph_3', 4, '{{1, 1, 0, 0}, {1, 1, 1, 0}, {0, 1, 1, 1}, {0, 0, 1, 1}}', TRUE, 2, TRUE, 3, 1, 0.5, 2),
                                                                                                                                                 ('graph_4', 4, '{{1, 1, 1, 0}, {1, 1, 1, 1}, {1, 1, 1, 1}, {0, 1, 1, 1}}', TRUE, 3, FALSE, 5, 1, 0.8333, 3),
                                                                                                                                                 ('graph_5', 4, '{{1, 1, 0, 1}, {1, 1, 1, 0}, {0, 1, 1, 1}, {1, 0, 1, 1}}', TRUE, 2, TRUE, 4, 1, 0.6667, 2),
                                                                                                                                                 ('graph_6', 5, '{{1, 1, 0, 0, 1}, {1, 1, 1, 0, 0}, {0, 1, 1, 1, 0}, {0, 0, 1, 1, 1}, {1, 0, 0, 1, 1}}', TRUE, 2, TRUE, 5, 1, 0.5, 2),
                                                                                                                                                 ('graph_7', 5, '{{1, 1, 1, 0, 0}, {1, 1, 1, 1, 0}, {1, 1, 1, 1, 1}, {0, 1, 1, 1, 1}, {0, 0, 1, 1, 1}}', TRUE, 3, FALSE, 8, 1, 0.8, 4),
                                                                                                                                                 ('graph_8', 5, '{{1, 1, 0, 1, 1}, {1, 1, 1, 0, 0}, {0, 1, 1, 1, 1}, {1, 0, 1, 1, 0}, {1, 0, 1, 0, 1}}', TRUE, 2, TRUE, 6, 1, 0.6, 3),
                                                                                                                                                 ('graph_9', 5, '{{1, 1, 1, 0, 0}, {1, 1, 0, 1, 0}, {1, 0, 1, 1, 1}, {0, 1, 1, 1, 1}, {0, 0, 1, 1, 1}}', TRUE, 2, TRUE, 7, 1, 0.7, 3),
                                                                                                                                                 ('graph_10', 5, '{{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}}', TRUE, 5, FALSE, 10, 1, 1.0, 4);

INSERT INTO coloring (graph_id, coloring) VALUES
                                              ('graph_1', ARRAY[0, 1, 0]),
                                              ('graph_1', ARRAY[1, 0, 1]);

INSERT INTO coloring (graph_id, coloring) VALUES
                                              ('graph_2', ARRAY[0, 1, 2]),
                                              ('graph_2', ARRAY[1, 0, 2]);

INSERT INTO coloring (graph_id, coloring) VALUES
                                              ('graph_3', ARRAY[0, 1, 0, 1]),
                                              ('graph_3', ARRAY[1, 0, 1, 0]);

INSERT INTO coloring (graph_id, coloring) VALUES
                                              ('graph_4', ARRAY[0, 1, 2, 0]),
                                              ('graph_4', ARRAY[1, 0, 2, 1]);

INSERT INTO coloring (graph_id, coloring) VALUES
                                              ('graph_5', ARRAY[0, 1, 0, 1]),
                                              ('graph_5', ARRAY[1, 0, 1, 0]);

INSERT INTO coloring (graph_id, coloring) VALUES
                                              ('graph_6', ARRAY[0, 1, 0, 1, 0]),
                                              ('graph_6', ARRAY[1, 0, 1, 0, 1]);

INSERT INTO coloring (graph_id, coloring) VALUES
                                              ('graph_7', ARRAY[0, 1, 2, 1, 0]),
                                              ('graph_7', ARRAY[1, 0, 2, 0, 1]);

INSERT INTO coloring (graph_id, coloring) VALUES
                                              ('graph_8', ARRAY[0, 1, 0, 1, 0]),
                                              ('graph_8', ARRAY[1, 0, 1, 0, 1]);

INSERT INTO coloring (graph_id, coloring) VALUES
                                              ('graph_9', ARRAY[0, 1, 0, 1, 0]),
                                              ('graph_9', ARRAY[1, 0, 1, 0, 1]);

INSERT INTO coloring (graph_id, coloring) VALUES
                                              ('graph_10', ARRAY[0, 1, 2, 3, 4]),
                                              ('graph_10', ARRAY[4, 3, 2, 1, 0]);
