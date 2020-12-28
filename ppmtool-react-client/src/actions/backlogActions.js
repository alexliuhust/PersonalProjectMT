import axios from "axios";
import { GET_BACKLOG, GET_PROJECT_TASK, DELETE_PROJECT_TASK } from "./types";

export const addProjectTask = (backlog_id, projectTask, history) => async (
  dispatch
) => {
  await axios.post(`/api/backlog/${backlog_id}`, projectTask);
  history.push(`/projectBoard/${backlog_id}`);
};
